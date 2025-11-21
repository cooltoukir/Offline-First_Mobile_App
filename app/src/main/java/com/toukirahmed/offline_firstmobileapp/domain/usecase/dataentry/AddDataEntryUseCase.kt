package com.toukirahmed.offline_firstmobileapp.domain.usecase.dataentry

import com.toukirahmed.offline_firstmobileapp.domain.model.DataEntryModel
import com.toukirahmed.offline_firstmobileapp.domain.repository.DataEntryRepository
import javax.inject.Inject

class AddDataEntryUseCase @Inject constructor(
    private val repository: DataEntryRepository
) {
    suspend operator fun invoke(entry: DataEntryModel) {
        repository.addDataEntry(entry)
    }
}