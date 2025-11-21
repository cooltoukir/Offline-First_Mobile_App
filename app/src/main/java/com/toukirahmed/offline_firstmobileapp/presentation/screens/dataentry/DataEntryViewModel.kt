package com.toukirahmed.offline_firstmobileapp.presentation.screens.dataentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toukirahmed.offline_firstmobileapp.domain.model.DataEntryModel
import com.toukirahmed.offline_firstmobileapp.domain.usecase.dataentry.AddDataEntryUseCase
import com.toukirahmed.offline_firstmobileapp.domain.usecase.dataentry.GetAllDataEntriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class DataEntryViewModel @Inject constructor(
    private val addDataEntryUseCase: AddDataEntryUseCase,
    getAllDataEntriesUseCase: GetAllDataEntriesUseCase
) : ViewModel() {

    val allEntries: StateFlow<List<DataEntryModel>> =
        getAllDataEntriesUseCase().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun saveEntry(name: String, age: Int, remarks: String, onSaved: () -> Unit) {
        val entry = DataEntryModel(
            id = UUID.randomUUID().toString(),
            name = name,
            age = age,
            remarks = remarks,
            isSynced = false
        )

        viewModelScope.launch {
            addDataEntryUseCase(entry)
            onSaved()
        }
    }
}