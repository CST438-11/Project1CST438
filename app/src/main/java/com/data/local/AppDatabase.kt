package com.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 3,
     exportSchema = false // <-- turn that on when I'm worrying about migrations later
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}