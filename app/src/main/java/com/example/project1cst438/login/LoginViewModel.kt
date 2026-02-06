package com.example.project1cst438.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.local.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var loginResult = mutableStateOf<Boolean?>(null)

    fun onLoginClicked() {
        viewModelScope.launch() {
            var user = repository.login(username.value, password.value)
            loginResult.value = (user != null)
        }
    }
}