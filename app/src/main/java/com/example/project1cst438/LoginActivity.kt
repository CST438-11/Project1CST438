package com.example.project1cst438

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.project1cst438.ui.theme.Project1CST438Theme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1CST438Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginFormat(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun LoginFormat(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = modifier, verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
        Text(
            "Enter Credentials"
        )

        TextField(
            value = username,
            onValueChange = { newText -> username = newText},
            placeholder = {Text("Enter username")},
        )

        TextField(
            value = password,
            onValueChange = { newText -> password = newText},
            placeholder = {Text("Enter password")},
//            Hides the password when typing
            visualTransformation = PasswordVisualTransformation()
        )

        Button(onClick = {println("Username: $username, Password: $password")}) {
            Text("Login")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Project1CST438Theme {
        LoginFormat()
    }
}