package com.toukirahmed.offline_firstmobileapp.domain.usecase.dataentry

import com.toukirahmed.offline_firstmobileapp.domain.model.DataEntryModel
import com.toukirahmed.offline_firstmobileapp.domain.repository.DataEntryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDataEntriesUseCase @Inject constructor(
    private val repository: DataEntryRepository
) {
    operator fun invoke(): Flow<List<DataEntryModel>> = repository.getAllDataEntries()
}