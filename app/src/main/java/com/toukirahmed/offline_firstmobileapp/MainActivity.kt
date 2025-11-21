package com.toukirahmed.offline_firstmobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.toukirahmed.offline_firstmobileapp.presentation.navigation.NavigationHost
import com.toukirahmed.offline_firstmobileapp.presentation.utils.InternetSnackBar
import com.toukirahmed.offline_firstmobileapp.presentation.ui.theme.OfflineFirstMobileAppTheme
import com.toukirahmed.offline_firstmobileapp.utils.NetworkObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var networkObserver: NetworkObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkObserver = NetworkObserver(this)
        networkObserver.startObserving()

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
                        NavigationHost(
                        )

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