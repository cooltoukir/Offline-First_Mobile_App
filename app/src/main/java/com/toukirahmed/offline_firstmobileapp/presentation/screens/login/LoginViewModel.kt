package com.toukirahmed.offline_firstmobileapp.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toukirahmed.offline_firstmobileapp.domain.usecase.auth.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val saveTokenUseCase: SaveTokenUseCase
) : ViewModel() {

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            saveTokenUseCase("dummy_token_123")
            onSuccess()
        }
    }
}