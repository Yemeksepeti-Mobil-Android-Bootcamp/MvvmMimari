package com.kodluyoruz.mvvmandroid.data.local

import com.kodluyoruz.mvvmandroid.utils.room.LocalUser
import com.kodluyoruz.mvvmandroid.utils.room.UserDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    val sharedPrefManager: SharedPrefManager,
    val userDao: UserDao
) {

    fun saveToken(token: String) {
        sharedPrefManager.saveToken(token)
    }

    fun getToken(): String? {
        return sharedPrefManager.getToken()
    }

    fun listUsers(): List<LocalUser> = userDao.listUsers()

    fun addUser(localUser: LocalUser) {
        userDao.addUser(localUser)
    }

    fun removeUser(localUser: LocalUser) {
        userDao.removeUser(localUser)
    }
}