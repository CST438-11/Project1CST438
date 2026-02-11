package com.example.project1cst438.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.data.local.UserRepository

/*
* This factory tells Android exactly how to create SignUpViewModel and pass in the repository it needs.
* Without this, SignUpViewModel can't be created because Android doesn't know SignUpViewModel()
 */
class SignUpViewModelFactory(private val repo : UserRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        Android is checking if its asking for SignUpViewModel, if it is then create that ViewModel
//        Otherwise throw an error
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(repo) as T

        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}