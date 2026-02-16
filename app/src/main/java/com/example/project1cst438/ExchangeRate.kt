package com.example.project1cst438

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project1cst438.ui.screens.ExchangeRateViewModel
import com.example.project1cst438.ui.theme.Project1CST438Theme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.ui.platform.LocalContext
import com.data.local.DatabaseProvider
import com.data.local.UserRepository
import com.example.project1cst438.ui.screens.ExchangeRateViewModelFactory


class Display : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Project1CST438Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ExchangeRateScreen(modifier = Modifier.padding(innerPadding),
                    onBackToHome = { finish() }
                    )
                }
            }
        }
    }
}


@Composable
fun ExchangeRateScreen(
    modifier: Modifier = Modifier,
    onBackToHome: () -> Unit) {


    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val db = remember { DatabaseProvider.getDatabase(context) }
    val userRepo = remember { UserRepository(db.userDao()) }

    val viewModel: ExchangeRateViewModel = viewModel(
        factory = ExchangeRateViewModel.provideFactory(userRepo)
    )
    val rates = viewModel.rates
    val currencyOptions = rates?.conversionRates
        ?.keys
        ?.toList()
        ?: emptyList()
    val error = viewModel.errorMessage
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 2.dp)) {
            Button(
                onClick = onBackToHome,
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text("Back to Home")
            }

            Button(
                onClick = {


                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pair conversion")
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {

                Button(
                    onClick = { expanded = true },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text("Base currency: ${viewModel.baseCurrency}")
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    currencyOptions.forEach { currency ->
                        DropdownMenuItem(
                            text = { Text(currency) },
                            onClick = {
                                expanded = false
                                viewModel.changeBaseCurrency(currency)
                            }
                        )
                    }
                }
            }

            Button(
                onClick = { viewModel.saveDefaultCurrency() },
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Save as default")
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
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
                            .padding(top = 10.dp)
                    ) {

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
}
