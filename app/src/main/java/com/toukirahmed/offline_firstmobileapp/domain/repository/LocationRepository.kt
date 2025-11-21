package com.toukirahmed.offline_firstmobileapp.domain.repository

import com.toukirahmed.offline_firstmobileapp.data.local.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

interface LocationRepository {
    suspend fun saveLocation(location: LocationEntity)
    fun getLast10Locations(): Flow<List<LocationEntity>>
}