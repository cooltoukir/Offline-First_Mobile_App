package com.toukirahmed.offline_firstmobileapp.domain.usecase.auth

import com.toukirahmed.offline_firstmobileapp.domain.repository.AuthRepository
import javax.inject.Inject

class ClearTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.clearToken()
}