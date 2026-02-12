package com.example.project1cst438

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project1cst438.ui.theme.Project1CST438Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Project1CST438Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                }
                HomeDisplay()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeDisplay() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_easyrate),
            contentDescription = "EasyRate App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 36.dp),
            contentScale = ContentScale.Crop
        )
        Text("Powered By")
        Image(
            painter = painterResource(id = R.drawable.logo_exchangerateapi),
            contentDescription = "Exchange Rate Api Logo",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(bottom = 100.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            modifier = Modifier.padding(horizontal = 20.dp)) {
            Button(
                onClick = {
                    context.startActivity(Intent(context, LoginActivity::class.java))
                },
                modifier = Modifier.width(100.dp)
            ) {
                Text("Login")
            }
            Button(
                onClick = {
                    context.startActivity(Intent(context, SignUpActivity::class.java))
                },
                modifier = Modifier.width(100.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray,
                    contentColor = Color.White
                )
            ) {
                Text("Sign Up")
            }

        }

        // Temp button to test display page
        val context = LocalContext.current
        Button(
            onClick = {
                context.startActivity(
                    Intent(context, Display::class.java)
                )
            },
            modifier = Modifier
                .padding(bottom = 40.dp)
                .fillMaxWidth(0.6f)
        ) {
            Text("View Exchange Rates")
        }
    }
}
