package com.toukirahmed.offline_firstmobileapp.presentation.screens.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.toukirahmed.offline_firstmobileapp.domain.model.MenuItemModel
import com.toukirahmed.offline_firstmobileapp.presentation.screens.location.LocationViewModel

@Composable
fun DrawerContent(
    menuItems: List<MenuItemModel>,
    selectedItem: String,
    onMenuClick: (MenuItemModel) -> Unit,
    locationViewModel: LocationViewModel
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1E1E2E))
            .padding(16.dp)
    ) {
        Text(
            text = "Menu",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        menuItems.forEach { item ->
            val isSelected = item.title == selectedItem

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        if (isSelected) Color(0xFF3D3D5C) else Color.Transparent
                    )
                    .clickable {
                        if (item.title == "Logout") {
                            locationViewModel.stopLocationService(context)
                        }
                        onMenuClick(item)
                    }
                    .padding(14.dp)
            ) {
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.title,
                    tint = Color.White
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    item.title,
                    color = Color.White
                )
            }
        }
    }
}