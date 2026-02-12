package com.example.project1cst438

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1cst438.ui.screens.ExchangeRateViewModel
import com.example.project1cst438.ui.theme.Project1CST438Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon


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
fun ExchangeRateScreen(
    modifier: Modifier = Modifier,
    viewModel: ExchangeRateViewModel = viewModel()) {

    val rates = viewModel.rates
    val error = viewModel.errorMessage

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            error != null -> Text("Error: $error")
            rates == null -> CircularProgressIndicator()
            else -> {
                // Map -> sorted list so order is stable
                val ratesList = rates.conversionRates
                    .toList()               // List<Pair<String, Double>>
                    .sortedBy { it.first }  // sort by currency code

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    // Header row
                    item {
                        Text("Base: ${rates.baseCode}")
                        Divider(modifier = Modifier.padding(vertical = 12.dp))
                    }

                    items(ratesList) { (code, rate) ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // For each Row of data, display:
                            // Base currency (USD)
                            Text(
                                text = rates.baseCode,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            // Arrow icon
                            Icon(
                                imageVector = Icons.Default.ArrowForward,
                                contentDescription = "to",
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            // Conversion currency
                            Text(
                                text = "$code :",
                                modifier = Modifier.weight(1f)
                            )
                            // Rate aligned to right
                            Text(
                                text = String.format("%.4f", rate)
                            )
                        }
                        Divider()
                    }
                }
            }
        }
    }
}