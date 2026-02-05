package com.example.project1cst438

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1cst438.ui.screens.ExchangeRateViewModel
import com.example.project1cst438.ui.theme.Project1CST438Theme
import okhttp3.internal.connection.Exchange

class Display : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1CST438Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExchangeRateScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Project1CST438Theme {
        Greeting("Android")
    }
}

@Composable
fun ExchangeRateScreen(
    modifier: Modifier = Modifier,
    viewModel: ExchangeRateViewModel = viewModel()) {

//    val rates = viewModel.rates
    val raw = viewModel.rawResponse
    val scrollState = rememberScrollState()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        if (rates == null) {
//            CircularProgressIndicator()
//        } else {
//            Text("USD → EUR: ${rates.conversion_rates["EUR"]}")
//        }
        if (raw == null) {
            CircularProgressIndicator()
        } else {
            Text(
                text = raw,
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(scrollState)
            )
        }
    }
}