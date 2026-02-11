package com.example.project1cst438.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.data.local.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SignUpViewModel(private val repo: UserRepository) : ViewModel() {
//    Text Field, will use mutableStateOf to see the current text
//    and also private set which is like a getter & setter, can get the data publicly, but can only set data in this class
//    mutableStateOf is holding a state and telling Compose if there are changes
    var username by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var confirmPassword  by mutableStateOf("")
        private set

//    Error, String? means the value can either be String or null
    var userNameError by mutableStateOf<String?>(null)
        private set
    var passwordError by mutableStateOf<String?>(null)
        private set
    var confirmPaswordError by mutableStateOf<String?>(null)
        private set

//    Loading, helps prevent the user from clicking the button twice, basically its use is to disable the create button
    var isLoading by mutableStateOf(false)
        private set

//    UI variables
    var message by mutableStateOf<String?>(null)
        private set
    var canSwitch by mutableStateOf(false)
        private set

//    These functions will be called by the SignUpScreen (Compose) in SignUpActivity.kt, if there is any changes in the textfields
    fun onUserNameChange(value : String) {
        username = value
        userNameError = null
        message = null
    }

    fun onPasswordChange(value : String) {
        password = value
        passwordError = null
        message = null
    }

    fun onConfirmPasswordChange(value : String) {
        confirmPassword = value
        confirmPaswordError = null
        message = null
    }

//  Checks if user input is valid, if not then an error will show
    private fun validate() : Boolean {
        userNameError = null
        passwordError = null
        confirmPaswordError = null


//        Trims whiteSpaces and isOk checks if validation is valid
        val usrname = username.trim()
        var isOk = true

        if (usrname.isBlank() || usrname.length < 3) {
            userNameError = "Username must be atleast 3 characters"
            isOk = false
        }
        if (password.isBlank() || password.length < 8) {
            passwordError = "Password must be atleast 8 characters"
            isOk = false
        }
        if (confirmPassword.isBlank() || confirmPassword != password) {
            confirmPaswordError = "Passwords do not match";
            isOk = false
        }

        return isOk

    }

    fun clearMessage() {
        message = null
    }

//    Sign Up Button is clicked,
//    Now checking if we can actually insert user to database which we will call the SignUp in UserRepository
    fun onSignUpClicked(onResult: (Boolean) -> Unit) {
//    Asynchronous

        viewModelScope.launch {
            //        Call the validate function
            if (!validate()) {
//            Fail
                onResult(false)

            } else {
                val success = repo.signUp(
                    username.trim(),
                    password.trim()
                )
                onResult(success)
            }
        }
    }

}