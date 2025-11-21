package com.toukirahmed.offline_firstmobileapp.domain.usecase.location

import com.toukirahmed.offline_firstmobileapp.data.local.entity.LocationEntity
import com.toukirahmed.offline_firstmobileapp.domain.repository.LocationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLast10LocationsUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    operator fun invoke(): Flow<List<LocationEntity>> = repository.getLast10Locations()
}