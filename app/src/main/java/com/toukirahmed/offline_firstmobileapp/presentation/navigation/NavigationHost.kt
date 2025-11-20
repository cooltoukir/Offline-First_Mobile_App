package com.toukirahmed.offline_firstmobileapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator

@Composable
fun NavigationHost(modifier: Modifier = Modifier) {
    val backStack = rememberNavBackStack(NavigationDestination.MovieDiscoveryScreen)

    var selectedTab: NavigationDestination by remember {
        mutableStateOf(NavigationDestination.MovieDiscoveryScreen)
    }

    val showBottomBar = when (backStack.lastOrNull()) {
        is NavigationDestination.MovieDiscoveryScreen,
        is NavigationDestination.FavoritesScreen -> true

        else -> false
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        selected = selectedTab is NavigationDestination.MovieDiscoveryScreen,
                        onClick = { selectedTab = NavigationDestination.MovieDiscoveryScreen },
                        icon = { Icon(Icons.Default.Movie, contentDescription = "Discovery") },
                        label = { Text("Discovery") }
                    )
                    NavigationBarItem(
                        selected = selectedTab is NavigationDestination.FavoritesScreen,
                        onClick = { selectedTab = NavigationDestination.FavoritesScreen },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites") },
                        label = { Text("Favorites") }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            entryDecorators = listOf(
                rememberSceneSetupNavEntryDecorator(),
                rememberSavedStateNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {
                entry<NavigationDestination.MovieDiscoveryScreen> {
//                    val viewModel: MovieViewModel = hiltViewModel()
//                    MovieDiscoveryScreen(
//                        viewModel = viewModel,
//                        onClick = { movieId ->
//                            backStack.add(NavigationDestination.MovieDetailsScreen(movieId))
//                        },
//                        onSearchClick = {
//                            backStack.add(NavigationDestination.MovieSearchScreen)
//                        }
//                    )
                }
            }
        )

        LaunchedEffect(selectedTab) {
            backStack.removeLastOrNull()
            backStack.add(selectedTab)
        }
    }
}