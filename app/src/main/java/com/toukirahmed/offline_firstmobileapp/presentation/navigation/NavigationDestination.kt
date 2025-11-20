package com.toukirahmed.offline_firstmobileapp.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface NavigationDestination : NavKey {
    @Serializable
    data object MovieDiscoveryScreen : NavigationDestination

    @Serializable
    data class MovieDetailsScreen(val movieId: Int) : NavigationDestination

    @Serializable
    data object FavoritesScreen : NavigationDestination

    @Serializable
    data object MovieSearchScreen : NavigationDestination
}