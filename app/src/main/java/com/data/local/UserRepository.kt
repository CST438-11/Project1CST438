package com.data.local

import com.data.local.User
import com.data.local.UserDao

class UserRepository(private val userDao: UserDao) {

    suspend fun signUp(username: String, password: String): Boolean {
//        Trim whitespaces
        val usernameTrimmed = username.trim()

//        Check if username already existed in database
        val isUserNameExisting = userDao.getByUsername(usernameTrimmed)
        if (isUserNameExisting != null) {
            return false
        }

//        Do a try (try & catch block) to see if it goes through with no problem, also id 0 bc
//        Room will auto increment it since it checks if it already has that id and will assign a new one
        userDao.insertUser(User(0,usernameTrimmed, password))
        return true

    }

    suspend fun login(username: String, password: String): User? {
        val user = userDao.getByUsername(username)
        if (user != null && user.password == password) {
            userDao.clearLoggedIn()
            userDao.setLoggedIn(user.id)
            return user
        }
        return null
    }

    suspend fun deleteUser(username: String): Boolean {
        return userDao.deleteByUsername(username) > 0
    }

    // Get user's Id from repo
    suspend fun getLoggedInUserId(): Int? = userDao.getLoggedInUserId()

    // Update saved currency with user id
    suspend fun setPreferredCurrencyForLoggedInUser(currency: String): Boolean {
        val userId = userDao.getLoggedInUserId() ?: return false
        userDao.updatePreferredCurrency(userId, currency)
        return true
    }

    suspend fun getPreferredCurrencyForLoggedInUser(): String? {
        val userId = userDao.getLoggedInUserId() ?: return null
        return userDao.getPreferredCurrency(userId)
    }
}