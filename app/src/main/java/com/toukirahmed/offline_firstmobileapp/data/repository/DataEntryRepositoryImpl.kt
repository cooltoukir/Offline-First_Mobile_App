package com.toukirahmed.offline_firstmobileapp.data.repository

import com.toukirahmed.offline_firstmobileapp.data.local.dao.DataEntryDao
import com.toukirahmed.offline_firstmobileapp.data.local.entity.DataEntryEntity
import com.toukirahmed.offline_firstmobileapp.domain.model.DataEntryModel
import com.toukirahmed.offline_firstmobileapp.domain.repository.DataEntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataEntryRepositoryImpl @Inject constructor(
    private val dao: DataEntryDao
) : DataEntryRepository {

    override suspend fun addDataEntry(entry: DataEntryModel) {
        dao.insert(
            DataEntryEntity(
                id = entry.id,
                name = entry.name,
                age = entry.age,
                remarks = entry.remarks,
                isSynced = entry.isSynced
            )
        )
    }

    override fun getAllDataEntries(): Flow<List<DataEntryModel>> {
        return dao.getAllEntries().map { list ->
            list.map {
                DataEntryModel(it.id, it.name, it.age, it.remarks, it.isSynced)
            }
        }
    }
}