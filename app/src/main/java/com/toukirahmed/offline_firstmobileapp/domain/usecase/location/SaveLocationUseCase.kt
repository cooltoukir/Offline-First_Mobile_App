package com.toukirahmed.offline_firstmobileapp.domain.usecase.location

import com.toukirahmed.offline_firstmobileapp.data.local.entity.LocationEntity
import com.toukirahmed.offline_firstmobileapp.domain.repository.LocationRepository
import javax.inject.Inject

class SaveLocationUseCase @Inject constructor(
    private val repository: LocationRepository
) {
    suspend operator fun invoke(location: LocationEntity) {
        repository.saveLocation(location)
    }
}