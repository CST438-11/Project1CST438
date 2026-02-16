package com.example.project1cst438.login

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(viewModel: LoginViewModel, modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    val username by viewModel.username
    val password by viewModel.password
    val loginResult by viewModel.loginResult

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onBackClick) {
            Text("Back")
        }

        Text(
            "Enter Credentials",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = username,
            onValueChange = { viewModel.username.value = it },
            placeholder = { Text("Enter username") },
            textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier.testTag("username")
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { viewModel.password.value = it },
            placeholder = { Text("Enter password") },
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(fontSize = 18.sp),
            modifier = Modifier.testTag("password")
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { viewModel.onLoginClicked() },
            modifier = Modifier.width(170.dp).height(50.dp).testTag("loginButton")
        ) {
            Text("Login", fontSize = 18.sp)
        }

        // Show result message
        Spacer(Modifier.height(16.dp))

        loginResult?.let { success ->
            Text(
                text = if (success) "Login successful!" else "Invalid credentials",
                color = if (success) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
            )
        }
    }
}
