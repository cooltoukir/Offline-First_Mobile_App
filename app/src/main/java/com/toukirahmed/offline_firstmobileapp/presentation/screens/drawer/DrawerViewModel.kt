package com.toukirahmed.offline_firstmobileapp.presentation.screens.drawer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toukirahmed.offline_firstmobileapp.domain.model.MenuItemModel
import com.toukirahmed.offline_firstmobileapp.domain.usecase.auth.ClearTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawerViewModel @Inject constructor(
    private val clearTokenUseCase: ClearTokenUseCase
) : ViewModel() {

    val menuItems = listOf(
        MenuItemModel(1, "Home", android.R.drawable.ic_menu_view),
        MenuItemModel(2, "Data Entry", android.R.drawable.ic_menu_manage),
        MenuItemModel(3, "Settings", android.R.drawable.ic_menu_preferences),
        MenuItemModel(4, "Reports", android.R.drawable.ic_menu_agenda),
        MenuItemModel(5, "Logout", android.R.drawable.ic_lock_power_off)
    )

    fun logout(onLoggedOut: () -> Unit) {
        viewModelScope.launch {
            clearTokenUseCase()
            onLoggedOut()
        }
    }
}