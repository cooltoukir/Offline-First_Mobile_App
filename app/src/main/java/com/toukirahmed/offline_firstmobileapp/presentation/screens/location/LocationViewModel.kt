package com.toukirahmed.offline_firstmobileapp.presentation.screens.location

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toukirahmed.offline_firstmobileapp.domain.usecase.location.GetLast10LocationsUseCase
import com.toukirahmed.offline_firstmobileapp.service.LocationForegroundService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val getLast10LocationsUseCase: GetLast10LocationsUseCase
) : ViewModel() {

    fun startLocationService(context: Context) {
        LocationForegroundService.startService(context)
    }

    fun stopLocationService(context: Context) {
        LocationForegroundService.stopService(context)
    }

    val last10Locations = getLast10LocationsUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}