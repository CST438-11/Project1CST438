package com.example.project1cst438.ui.screens

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.data.local.UserRepository

class ExchangeRateViewModelFactory(
    private val application: Application,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeRateViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExchangeRateViewModel(application, userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
