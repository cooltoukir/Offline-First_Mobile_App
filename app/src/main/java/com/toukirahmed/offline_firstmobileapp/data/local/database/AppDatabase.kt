package com.toukirahmed.offline_firstmobileapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toukirahmed.offline_firstmobileapp.data.local.dao.DataEntryDao
import com.toukirahmed.offline_firstmobileapp.data.local.dao.LocationDao
import com.toukirahmed.offline_firstmobileapp.data.local.entity.DataEntryEntity
import com.toukirahmed.offline_firstmobileapp.data.local.entity.LocationEntity

@Database(
    entities = [DataEntryEntity::class, LocationEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataEntryDao(): DataEntryDao

    abstract fun locationDao(): LocationDao
}