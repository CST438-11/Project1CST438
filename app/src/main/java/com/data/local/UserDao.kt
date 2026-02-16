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

    @Query("DELETE FROM users WHERE username = :username")
    suspend fun deleteByUsername(username: String): Int

    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getByUsername(username: String): User?

    @Query("UPDATE users SET password = :newPassword WHERE username = :username")
    suspend fun updatePassword(username: String, newPassword: String): Int

    @Query("UPDATE users SET preferredCurrency = :newPreferredCurrency WHERE id = :userId")
    suspend fun updatePreferredCurrency(userId: Int, newPreferredCurrency: String): Int

    @Query("SELECT preferredCurrency FROM users WHERE id = :userId LIMIT 1")
    suspend fun getPreferredCurrency(userId: Int): String?

    @Query("UPDATE users SET isLoggedIn = 0")
    suspend fun clearLoggedIn()

    @Query("UPDATE users SET isLoggedIn = 1 WHERE id = :userId")
    suspend fun setLoggedIn(userId: Int)

    @Query("SELECT id FROM users WHERE isLoggedIn = 1 LIMIT 1")
    suspend fun getLoggedInUserId(): Int?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}