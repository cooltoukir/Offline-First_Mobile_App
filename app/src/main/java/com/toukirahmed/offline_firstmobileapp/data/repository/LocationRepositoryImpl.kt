package com.toukirahmed.offline_firstmobileapp.data.repository

import com.toukirahmed.offline_firstmobileapp.data.local.dao.LocationDao
import com.toukirahmed.offline_firstmobileapp.data.local.entity.LocationEntity
import com.toukirahmed.offline_firstmobileapp.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val dao: LocationDao
) : LocationRepository {

    override suspend fun saveLocation(location: LocationEntity) {
        dao.insert(location)
    }

    override fun getLast10Locations(): Flow<List<LocationEntity>> = dao.getLast10Locations()
}