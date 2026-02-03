package com.example.project1cst438

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
//    Dummy user to log in to see check if it's working or not
//    val keyword is basically a static variable that can't be changed, basically it's fixed to that value
    val dummyUsername = "dummy1"
    val dummyPassword = "pass1234"

//    The by remember saves the username/password text when user types
//    The value of username/password are then remembered when screen updates
//    So the text that was typed is not erased even while typing
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

//    Makes the column be centered vertically & horizontally. Also fillMaxSize means take up the whole space of the screen available from parent
    Column(modifier = modifier.fillMaxSize() ,verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally ) {
        Text(
            "Enter Credentials",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold

        )
//        Puts spacing between the Text and the username text field
        Spacer(Modifier.height(16.dp))

//        When user types onValueChange the current value (username) to the newText
//        placeholder is there to show a hint to the user on what to type
        TextField(
            value = username,
            onValueChange = { newText -> username = newText},
            placeholder = {Text("Enter username")},
            textStyle = TextStyle(fontSize = 18.sp)
        )

        Spacer(Modifier.height(16.dp))

        TextField(
            value = password,
            onValueChange = { newText -> password = newText},
            placeholder = {Text("Enter password")},
//            Hides the password when typing
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(fontSize = 18.sp)
        )

        Spacer(Modifier.height(16.dp))

//        When button is clicked it would print
        Button(onClick = {
//            Checks if dummyUsername was inputted correctly
//            If correct then successfully login, if not then failed
            if (username == dummyUsername && password == dummyPassword) {
                println("Login Success!!")
            } else {
                println("Invalid Credentials :(")
            }

            }, modifier = Modifier.width(170.dp).height(50.dp)) {

            Text("Login", fontSize = 18.sp)
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