package com.toukirahmed.offline_firstmobileapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.toukirahmed.offline_firstmobileapp.presentation.screens.dataentry.DataEntryScreen
import com.toukirahmed.offline_firstmobileapp.presentation.screens.dataentry.DataEntryViewModel
import com.toukirahmed.offline_firstmobileapp.presentation.screens.drawer.DrawerViewModel
import com.toukirahmed.offline_firstmobileapp.presentation.screens.home.HomeScreen
import com.toukirahmed.offline_firstmobileapp.presentation.screens.location.LocationHistoryScreen
import com.toukirahmed.offline_firstmobileapp.presentation.screens.location.LocationViewModel
import com.toukirahmed.offline_firstmobileapp.presentation.screens.login.LoginScreen
import com.toukirahmed.offline_firstmobileapp.presentation.screens.login.LoginViewModel
import com.toukirahmed.offline_firstmobileapp.presentation.screens.main.MainLayout
import com.toukirahmed.offline_firstmobileapp.presentation.screens.splash.SplashScreen
import com.toukirahmed.offline_firstmobileapp.presentation.screens.splash.SplashViewModel

@Composable
fun NavigationHost() {
    val backStack = rememberNavBackStack(NavigationDestination.Splash)
    val locationViewModel: LocationViewModel = hiltViewModel()

    Scaffold { innerPadding ->
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.padding(innerPadding),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            onBack = { backStack.removeLastOrNull() },
            entryProvider = entryProvider {

                entry<NavigationDestination.Splash> {
                    val vm: SplashViewModel = hiltViewModel()
                    SplashScreen(
                        viewModel = vm,
                        onNavigateLogin = {
                            backStack.clear()
                            backStack.add(NavigationDestination.Login)
                        },
                        onNavigateHome = {
                            backStack.clear()
                            backStack.add(NavigationDestination.Home)
                        },
                        locationViewModel = locationViewModel
                    )
                }

                entry<NavigationDestination.Login> {
                    val vm: LoginViewModel = hiltViewModel()
                    LoginScreen(
                        viewModel = vm,
                        navigateHome = {
                            backStack.clear()
                            backStack.add(NavigationDestination.Home)
                        }
                    )
                }

                entry<NavigationDestination.Home> {
                    val drawerVM: DrawerViewModel = hiltViewModel()
                    MainLayout(
                        menuItems = drawerVM.menuItems,
                        selectedItem = "Home",
                        onMenuClick = { item ->
                            when (item.title) {
                                "Home" -> backStack.add(NavigationDestination.Home)
                                "Data Entry" -> backStack.add(NavigationDestination.DataEntry)
                                "Location History" -> backStack.add(NavigationDestination.LocationHistory)
//                                "Reports" -> backStack.add(NavigationDestination.Reports)
                                "Logout" -> drawerVM.logout {
                                    backStack.clear()
                                    backStack.add(NavigationDestination.Login)
                                }
                            }
                        },
                        locationViewModel = locationViewModel
                    ) {
                        HomeScreen()
                    }
                }

                entry<NavigationDestination.DataEntry> {
                    val drawerVM: DrawerViewModel = hiltViewModel()
                    val vm: DataEntryViewModel = hiltViewModel()
                    MainLayout(
                        menuItems = drawerVM.menuItems,
                        selectedItem = "Data Entry",
                        onMenuClick = { item ->
                            when (item.title) {
                                "Home" -> backStack.add(NavigationDestination.Home)
                                "Data Entry" -> {}
                                "Location History" -> backStack.add(NavigationDestination.LocationHistory)
//                                "Reports" -> backStack.add(NavigationDestination.Reports)
                                "Logout" -> drawerVM.logout {
                                    backStack.clear()
                                    backStack.add(NavigationDestination.Login)
                                }
                            }
                        },
                        locationViewModel = locationViewModel
                    ) {
                        DataEntryScreen(viewModel = vm)
                    }
                }

                entry<NavigationDestination.LocationHistory> {
                    val drawerVM: DrawerViewModel = hiltViewModel()
                    val locationVM: LocationViewModel = hiltViewModel()
                    MainLayout(
                        menuItems = drawerVM.menuItems,
                        selectedItem = "Location History",
                        onMenuClick = { item ->
                            when (item.title) {
                                "Home" -> backStack.add(NavigationDestination.Home)
                                "Data Entry" -> backStack.add(NavigationDestination.DataEntry)
                                "Location History" -> {}
                                "Logout" -> drawerVM.logout {
                                    backStack.clear()
                                    backStack.add(NavigationDestination.Login)
                                }
                            }
                        },
                        locationViewModel = locationVM
                    ) {
                        LocationHistoryScreen(viewModel = locationVM)
                    }
                }
            }
        )
    }
}