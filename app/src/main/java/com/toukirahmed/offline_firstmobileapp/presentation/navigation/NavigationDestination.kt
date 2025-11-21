package com.toukirahmed.offline_firstmobileapp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface NavigationDestination : NavKey {

    @Serializable
    data object Splash : NavigationDestination

    @Serializable
    data object Login : NavigationDestination

    @Serializable
    data object Home : NavigationDestination

    @Serializable
    data object DataEntry : NavigationDestination

    @Serializable
    data object LocationHistory : NavigationDestination
}