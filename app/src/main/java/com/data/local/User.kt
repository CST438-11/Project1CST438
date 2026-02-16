package com.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [Index(value = ["username"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password: String, //TODO: should salt and hash password
    val preferredCurrency: String = "USD", // String for currency key
    val isLoggedIn: Boolean = false // Used to tell which user is logged in
)