package com.example.project1cst438

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.data.local.DatabaseProvider
import com.data.local.UserRepository
import com.example.project1cst438.login.LoginScreen
import com.example.project1cst438.login.LoginViewModel
import com.example.project1cst438.login.LoginViewModelFactory
import com.example.project1cst438.ui.theme.Project1CST438Theme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set up database and repository
        val database = DatabaseProvider.getDatabase(applicationContext)
        val repository = UserRepository(database.userDao())

        // Create ViewModel using factory
        val viewModelFactory = LoginViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            Project1CST438Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}