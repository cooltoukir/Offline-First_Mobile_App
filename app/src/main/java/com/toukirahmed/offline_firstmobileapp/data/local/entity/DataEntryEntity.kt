package com.toukirahmed.offline_firstmobileapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_entries")
data class DataEntryEntity(
    @PrimaryKey val id: String,
    val name: String,
    val age: Int,
    val remarks: String,
    val isSynced: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)