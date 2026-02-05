package com.example.project1cst438.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.project1cst438.model.ExchangeRateResponse
import kotlinx.coroutines.launch
import com.example.project1cst438.network.ExchangeRateApi

sealed interface ExchangeRateUiState {
    data class Success(val rates: String) : ExchangeRateUiState
    object Error : ExchangeRateUiState
    object Loading : ExchangeRateUiState
}

class ExchangeRateViewModel: ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var rates by mutableStateOf<ExchangeRateResponse?>(null)
        private set

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        fetchRates()
    }

    private fun fetchRates() {
        viewModelScope.launch {
            try {
                rates = ExchangeRateApi.retrofitService.getRates()
            } catch (e: Exception) {
                Log.e("API", "Error: ${e.message}")
            }
        }
    }

//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
//                val marsPhotosRepository = application.container.marsPhotosRepository
//                MarsViewModel(marsPhotosRepository = marsPhotosRepository)
//            }
//        }
//    }
}