package com.toukirahmed.offline_firstmobileapp.presentation.screens.location

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LocationHistoryScreen(
    viewModel: LocationViewModel
) {
    val locations by viewModel.last10Locations.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(locations) { loc ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Latitude: ${loc.latitude}", style = MaterialTheme.typography.bodyMedium)
                Text("Longitude: ${loc.longitude}", style = MaterialTheme.typography.bodyMedium)
                Text("Timestamp: ${loc.timestamp}", style = MaterialTheme.typography.bodyMedium)
                Text("Synced: ${loc.isSynced}", style = MaterialTheme.typography.bodyMedium)
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    thickness = DividerDefaults.Thickness,
                    color = DividerDefaults.color
                )
            }
        }
    }
}