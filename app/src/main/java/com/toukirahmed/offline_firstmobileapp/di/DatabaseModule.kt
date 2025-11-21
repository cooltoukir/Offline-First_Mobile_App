package com.toukirahmed.offline_firstmobileapp.di

import android.content.Context
import androidx.room.Room
import com.toukirahmed.offline_firstmobileapp.data.local.dao.DataEntryDao
import com.toukirahmed.offline_firstmobileapp.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideDataEntryDao(db: AppDatabase): DataEntryDao = db.dataEntryDao()
}