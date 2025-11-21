package com.toukirahmed.offline_firstmobileapp.domain.model

data class DataEntryModel(
    val id: String,
    val name: String,
    val age: Int,
    val remarks: String,
    val isSynced: Boolean = false
)