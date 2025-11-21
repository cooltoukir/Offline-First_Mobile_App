package com.toukirahmed.offline_firstmobileapp.data.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.toukirahmed.offline_firstmobileapp.utils.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = Constants.USER_PREFS)

class UserPreferences @Inject constructor(
    @param:ApplicationContext private val context: Context
) {
    private val TOKEN_KEY = stringPreferencesKey(Constants.AUTH_TOKEN)

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    fun getToken() = context.dataStore.data.map { prefs ->
        prefs[TOKEN_KEY] ?: ""
    }

    suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }
}