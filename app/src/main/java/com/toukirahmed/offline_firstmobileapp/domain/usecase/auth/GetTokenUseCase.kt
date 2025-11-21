package com.toukirahmed.offline_firstmobileapp.domain.usecase.auth

import com.toukirahmed.offline_firstmobileapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getToken()
}