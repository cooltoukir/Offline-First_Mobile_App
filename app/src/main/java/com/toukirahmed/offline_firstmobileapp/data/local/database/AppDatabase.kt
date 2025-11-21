package com.toukirahmed.offline_firstmobileapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.toukirahmed.offline_firstmobileapp.data.local.dao.DataEntryDao
import com.toukirahmed.offline_firstmobileapp.data.local.entity.DataEntryEntity

@Database(
    entities = [DataEntryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataEntryDao(): DataEntryDao
}