package com.example.project1cst438.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import com.example.project1cst438.network.ExchangeRateApi

sealed interface ExchangeRateUiState {
    data class Success(val rates: String) : ExchangeRateUiState
    object Error : ExchangeRateUiState
    object Loading : ExchangeRateUiState
}

class ExchangeRateViewModel(private val exhangeRateRepository: ExhangeRateRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var exhangeRateUiState: ExchangeRateUiState by mutableStateOf(ExhangeRateUiState.Loading)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            val listResult = ExhangeRateApi.retrofitService.getPhotos()
            exchangeRateUiState = listResult
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val marsPhotosRepository = application.container.marsPhotosRepository
                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
            }
        }
    }
}