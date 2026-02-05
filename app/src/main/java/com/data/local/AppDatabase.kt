package com.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1,
    // exportSchema = true // <-- turn that on when I'm worrying about migrations later
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}