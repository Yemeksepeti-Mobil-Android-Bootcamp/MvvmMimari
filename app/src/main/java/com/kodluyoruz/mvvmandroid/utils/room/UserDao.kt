package com.kodluyoruz.mvvmandroid.utils.room

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * FROM localuser")
    fun listUsers(): List<LocalUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: LocalUser)

    @Query("SELECT * FROM localuser WHERE userId=:userId LIMIT 1")
    fun getUserById(userId: Int): LocalUser

    @Delete
    fun removeUser(user: LocalUser)

}