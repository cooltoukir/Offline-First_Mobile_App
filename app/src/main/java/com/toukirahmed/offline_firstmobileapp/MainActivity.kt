package com.toukirahmed.offline_firstmobileapp

import android.Manifest
import android.app.Activity
import android.content.IntentSender
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsResponse
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.Task
import com.toukirahmed.offline_firstmobileapp.presentation.navigation.NavigationHost
import com.toukirahmed.offline_firstmobileapp.presentation.ui.theme.OfflineFirstMobileAppTheme
import com.toukirahmed.offline_firstmobileapp.presentation.utils.InternetSnackBar
import com.toukirahmed.offline_firstmobileapp.service.LocationForegroundService
import com.toukirahmed.offline_firstmobileapp.utils.Constants
import com.toukirahmed.offline_firstmobileapp.utils.LocationUtils
import com.toukirahmed.offline_firstmobileapp.utils.NetworkObserver
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var networkObserver: NetworkObserver

    private val locationSettingsLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                LocationForegroundService.startService(this)
            } else {
                Toast.makeText(this, "Location is not enabled", Toast.LENGTH_SHORT).show()
            }
        }

    private val backgroundLocationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Background location access granted.
                checkLocationSettings()
            } else {
                // Background location access denied.
            }
        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true || permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    requestBackgroundLocationPermission()
                } else {
                    checkLocationSettings()
                }
            }
        }

    private fun requestBackgroundLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            backgroundLocationPermissionLauncher.launch(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }
    }

    private fun checkLocationSettings() {
        if (LocationUtils.isLocationEnabled(this)) {
            LocationForegroundService.startService(this)
        } else {
            val locationRequest = LocationRequest.Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                TimeUnit.MINUTES.toMillis(Constants.LOCATION_UPDATE_INTERVAL_MINUTES)
            ).build()
            val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
            val client = LocationServices.getSettingsClient(this)
            val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

            task.addOnFailureListener { exception ->
                if (exception is ResolvableApiException) {
                    try {
                        val intentSenderRequest =
                            IntentSenderRequest.Builder(exception.resolution).build()
                        locationSettingsLauncher.launch(intentSenderRequest)
                    } catch (sendEx: IntentSender.SendIntentException) {

                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkObserver = NetworkObserver(this)
        networkObserver.startObserving()

        requestPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        enableEdgeToEdge()
        setContent {
            OfflineFirstMobileAppTheme {
                var showSnackBar by remember { mutableStateOf(false) }

                LaunchedEffect(networkObserver.isConnected) {
                    showSnackBar = !networkObserver.isConnected
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->

                    Box(modifier = Modifier.fillMaxSize()) {
                        NavigationHost()

                        InternetSnackBar(
                            isVisible = showSnackBar,
                            onDismiss = { showSnackBar = false }
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        networkObserver.stopObserving()
    }
}