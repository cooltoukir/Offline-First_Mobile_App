package com.toukirahmed.offline_firstmobileapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.toukirahmed.offline_firstmobileapp.data.local.entity.DataEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DataEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: DataEntryEntity)

    @Query("SELECT * FROM data_entries ORDER BY createdAt DESC")
    fun getAllEntries(): Flow<List<DataEntryEntity>>
}