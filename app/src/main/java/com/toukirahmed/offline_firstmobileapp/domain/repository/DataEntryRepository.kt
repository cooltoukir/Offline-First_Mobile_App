package com.toukirahmed.offline_firstmobileapp.domain.repository

import com.toukirahmed.offline_firstmobileapp.domain.model.DataEntryModel
import kotlinx.coroutines.flow.Flow

interface DataEntryRepository {
    suspend fun addDataEntry(entry: DataEntryModel)
    fun getAllDataEntries(): Flow<List<DataEntryModel>>
}