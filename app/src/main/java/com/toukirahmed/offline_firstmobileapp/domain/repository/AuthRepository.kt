package com.toukirahmed.offline_firstmobileapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun saveToken(token: String)
    fun getToken(): Flow<String>
    suspend fun clearToken()
}