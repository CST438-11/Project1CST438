package com.example.project1cst438

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.data.local.AppDatabase
import com.data.local.UserDao
import com.data.local.UserRepository
import com.example.project1cst438.ui.screens.SignUpViewModel
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//This @ is for asynchronous, when querying databases
@RunWith(AndroidJUnit4::class)
class SignUpTest {
//Before and after each test, run special setup/cleanup code using this rule.
    @get:Rule
//Set up a fake Compose screen before each test so I can test my UI.
    val composeTestRule = createComposeRule()

//initialize this variable later before using it
    private lateinit var db: AppDatabase
    private lateinit var userDao: UserDao
    private lateinit var repo: UserRepository


    @Before
    fun setup() {
//Starts Espresso’s intent monitoring system so your test can detect,
// verify, or fake (mock) Intent calls made by your app.
        Intents.init()

//Get the app’s main environment (Context) during a test to be able to use database,
// resources, or system services even though the app isn’t actually running on screen.
        val context = ApplicationProvider.getApplicationContext<Context>()

//Use Room to create a temporary database that exists only in memory (not saved to the device),
// using the app context and the AppDatabase class structure, and then build it so it’s ready to
// use for testing.”
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

//“Get the UserDao (the object that contains the database functions like insert, delete, and query)
// from the db database so it can be used to interact with the database in the test
        userDao = db.userDao()

        repo = UserRepository(db.userDao())
    }

    @After
    fun teardown() {
//Stops Espresso’s intent monitoring and cleans everything up after the test finishes
        Intents.release()
        db.close()
    }

    @Test
    fun signupUser() {
        val fakeViewModel = SignUpViewModel(repo)

        composeTestRule.setContent {
            SignUpScreen(onBack = {}, viewModelz = fakeViewModel)
        }

        composeTestRule.onNodeWithTag("name").performTextInput("new_user")
        composeTestRule.onNodeWithTag("password").performTextInput("new_password")
        composeTestRule.onNodeWithTag("confirmPassword").performTextInput("new_password")

        composeTestRule.onNodeWithTag("button").performClick()
//runBlocking basically starts a couroutine and blocks currents thread until it finishes
//Essentially, it is saying, Run this suspend code right now, and don’t continue until it’s done
        composeTestRule.waitUntil(timeoutMillis = 5_000) {
            runBlocking { userDao.getByUsername("new_user") != null }
        }

        val userInserted =  runBlocking {userDao.getByUsername("new_user")}
        assert(userInserted != null)
        assert(userInserted!!.username == "new_user")

    }



}