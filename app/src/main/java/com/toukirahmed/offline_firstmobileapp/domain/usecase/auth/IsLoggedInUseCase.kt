package com.toukirahmed.offline_firstmobileapp.domain.usecase.auth

import com.toukirahmed.offline_firstmobileapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke() = repository.getToken().map { it.isNotEmpty() }
}