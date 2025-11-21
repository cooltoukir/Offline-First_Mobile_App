package com.toukirahmed.offline_firstmobileapp.data.repository

import com.toukirahmed.offline_firstmobileapp.data.local.datastore.UserPreferences
import com.toukirahmed.offline_firstmobileapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val prefs: UserPreferences
) : AuthRepository {

    override suspend fun saveToken(token: String) = prefs.saveToken(token)

    override fun getToken(): Flow<String> = prefs.getToken()

    override suspend fun clearToken() = prefs.clearToken()
}