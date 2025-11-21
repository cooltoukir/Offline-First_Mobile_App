package com.toukirahmed.offline_firstmobileapp.domain.usecase.auth

import com.toukirahmed.offline_firstmobileapp.domain.repository.AuthRepository
import javax.inject.Inject

class SaveTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(token: String) = repository.saveToken(token)
}