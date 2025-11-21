package com.toukirahmed.offline_firstmobileapp.service

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.google.android.gms.location.*
import com.toukirahmed.offline_firstmobileapp.data.local.entity.LocationEntity
import com.toukirahmed.offline_firstmobileapp.domain.usecase.location.SaveLocationUseCase
import com.toukirahmed.offline_firstmobileapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class LocationForegroundService : Service() {

    @Inject
    lateinit var saveLocationUseCase: SaveLocationUseCase

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        startForegroundService()

        startImmediateLocation()

        serviceScope.launch {
            kotlinx.coroutines.delay(
                TimeUnit.MINUTES.toMillis(Constants.LOCATION_UPDATE_INTERVAL_MINUTES)
            )
            startLocationUpdates()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY

    private fun startForegroundService() {
        val channelId = "location_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId, "Location Service", NotificationManager.IMPORTANCE_HIGH
            )
            getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
        }

        val notification: Notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("Tracking location")
            .setContentText("Location service is running in background")
            .setSmallIcon(android.R.drawable.ic_menu_mylocation)
            .build()

        startForeground(1, notification)
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun saveLocation(lat: Double, lon: Double) {
        serviceScope.launch {
            saveLocationUseCase(LocationEntity(latitude = lat, longitude = lon))
        }
    }

    private fun startImmediateLocation() {
        if (!checkPermission()) {
            stopSelf(); return
        }

        try {
            fusedLocationClient.lastLocation.addOnSuccessListener { loc ->
                if (loc != null) {
                    Log.d("LocationService", "Immediate: ${loc.latitude}, ${loc.longitude}")
                    saveLocation(loc.latitude, loc.longitude)
                } else {
                    requestSingleLocation()
                }
            }
        } catch (e: SecurityException) {
            stopSelf()
        }
    }

    private fun requestSingleLocation() {
        if (!checkPermission()) return

        val req = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 0)
            .setMaxUpdates(1)
            .build()

        try {
            fusedLocationClient.requestLocationUpdates(
                req,
                object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        val loc = result.lastLocation ?: return
                        Log.d("LocationService", "Single: ${loc.latitude}, ${loc.longitude}")
                        saveLocation(loc.latitude, loc.longitude)
                        fusedLocationClient.removeLocationUpdates(this)
                    }
                },
                Looper.getMainLooper()
            )
        } catch (_: SecurityException) {
        }
    }

    private fun startLocationUpdates() {
        if (!checkPermission()) {
            stopSelf(); return
        }

        val locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            TimeUnit.MINUTES.toMillis(Constants.LOCATION_UPDATE_INTERVAL_MINUTES)
        )
            .setMinUpdateIntervalMillis(TimeUnit.MINUTES.toMillis(Constants.LOCATION_UPDATE_INTERVAL_MINUTES))
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                result.locations.forEach { loc ->
                    Log.d("LocationService", "Periodic: ${loc.latitude}, ${loc.longitude}")
                    saveLocation(loc.latitude, loc.longitude)
                }
            }
        }

        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        } catch (_: SecurityException) {
            stopSelf()
        }
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopLocationUpdates()
        serviceScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    companion object {
        fun startService(context: Context) {
            val intent = Intent(context, LocationForegroundService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
                context.startForegroundService(intent)
            else context.startService(intent)
        }

        fun stopService(context: Context) {
            context.stopService(Intent(context, LocationForegroundService::class.java))
        }
    }
}