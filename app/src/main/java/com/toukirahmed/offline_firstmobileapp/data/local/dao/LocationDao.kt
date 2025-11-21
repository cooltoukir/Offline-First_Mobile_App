package com.toukirahmed.offline_firstmobileapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.toukirahmed.offline_firstmobileapp.data.local.entity.LocationEntity

@Dao
interface LocationDao {
    @Insert
    suspend fun insert(location: LocationEntity)

    @Query("SELECT * FROM locations ORDER BY timestamp DESC LIMIT 10")
    fun getLast10Locations(): Flow<List<LocationEntity>>
}