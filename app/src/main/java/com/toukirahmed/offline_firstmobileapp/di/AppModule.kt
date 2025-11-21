package com.toukirahmed.offline_firstmobileapp.di

import android.content.Context
import com.toukirahmed.offline_firstmobileapp.data.local.dao.DataEntryDao
import com.toukirahmed.offline_firstmobileapp.data.local.dao.LocationDao
import com.toukirahmed.offline_firstmobileapp.data.local.datastore.UserPreferences
import com.toukirahmed.offline_firstmobileapp.data.repository.AuthRepositoryImpl
import com.toukirahmed.offline_firstmobileapp.data.repository.DataEntryRepositoryImpl
import com.toukirahmed.offline_firstmobileapp.data.repository.LocationRepositoryImpl
import com.toukirahmed.offline_firstmobileapp.domain.repository.AuthRepository
import com.toukirahmed.offline_firstmobileapp.domain.repository.DataEntryRepository
import com.toukirahmed.offline_firstmobileapp.domain.repository.LocationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context) = UserPreferences(context)

    @Provides
    @Singleton
    fun provideAuthRepository(
        prefs: UserPreferences
    ): AuthRepository = AuthRepositoryImpl(prefs)

    @Provides
    @Singleton
    fun provideDataEntryRepository(
        dataEntryDao: DataEntryDao
    ): DataEntryRepository = DataEntryRepositoryImpl(dao = dataEntryDao)

    @Provides
    @Singleton
    fun provideLocationRepository(
        dao: LocationDao
    ): LocationRepository = LocationRepositoryImpl(dao = dao)
}