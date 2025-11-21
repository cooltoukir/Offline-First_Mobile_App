package com.toukirahmed.offline_firstmobileapp.domain.model

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable

@Serializable
data class MenuItemModel(
    val id: Int,
    val title: String,
    @param:DrawableRes val icon: Int
)