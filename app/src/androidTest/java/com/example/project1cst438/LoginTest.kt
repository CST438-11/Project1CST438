package com.example.project1cst438

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.data.local.AppDatabase
import com.data.local.User
import com.data.local.UserDao
import com.data.local.UserRepository
import com.example.project1cst438.login.LoginScreen
import com.example.project1cst438.login.LoginViewModel
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var repo: UserRepository

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        userDao = db.userDao()
        repo = UserRepository(userDao)
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun login_withValidCredentials_succeeds() = runBlocking {
        userDao.insertUser(User(0, "testuser", "password123"))

        val viewModel = LoginViewModel(repo)

        composeTestRule.setContent {
            LoginScreen(viewModel = viewModel, onBackClick = {})
        }

        composeTestRule.onNodeWithTag("username").performTextInput("testuser")
        composeTestRule.onNodeWithTag("password").performTextInput("password123")
        composeTestRule.onNodeWithTag("loginButton").performClick()

        composeTestRule.waitForIdle()

        assert(viewModel.loginResult.value == true)
    }

    @Test
    fun login_withWrongPassword_fails() = runBlocking {
        userDao.insertUser(User(0, "testuser", "password123"))

        val viewModel = LoginViewModel(repo)

        composeTestRule.setContent {
            LoginScreen(viewModel = viewModel, onBackClick = {})
        }

        composeTestRule.onNodeWithTag("username").performTextInput("testuser")
        composeTestRule.onNodeWithTag("password").performTextInput("wrongpassword")
        composeTestRule.onNodeWithTag("loginButton").performClick()

        composeTestRule.waitForIdle()

        assert(viewModel.loginResult.value == false)
    }

    @Test
    fun login_withNonexistentUser_fails() {
        val viewModel = LoginViewModel(repo)

        composeTestRule.setContent {
            LoginScreen(viewModel = viewModel, onBackClick = {})
        }

        composeTestRule.onNodeWithTag("username").performTextInput("nobody")
        composeTestRule.onNodeWithTag("password").performTextInput("password123")
        composeTestRule.onNodeWithTag("loginButton").performClick()

        composeTestRule.waitForIdle()

        assert(viewModel.loginResult.value == false)
    }
}