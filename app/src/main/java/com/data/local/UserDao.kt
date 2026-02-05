package com.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

    @Query("DELETE FROM users WHERE username = :username")
    suspend fun deleteByUsername(username: String): Int

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getByUsername(username: String): User?

    @Query("UPDATE users SET password = :newPassword WHERE username = :username")
    suspend fun updatePassword(username: String, newPassword: String): Int

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}