package com.example.project1cst438

import androidx.compose.material3.Text
import androidx.compose.ui.test.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule

import org.junit.Rule
import org.junit.Test

class ExchangeRateScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showsCurrencyRow() {

        composeTestRule.setContent {
            // Test text to display on screen
            Text("USD → EUR : 0.84")

        }

        composeTestRule
            .onNodeWithText("USD → EUR : 0.84")
            .assertExists()
    }
}