package com.kodluyoruz.mvvmandroid.utils

import android.content.Context
import android.util.Log
import androidx.room.*
import com.kodluyoruz.mvvmandroid.utils.room.AppDatabase

object RoomDbManager {
    fun initialize(context: Context) {
        val db = Room.databaseBuilder(context, AppDatabase::class.java, "LocalDb")
            .allowMainThreadQueries().build()
        val userDao = db.userDao()
        var list = userDao.listUsers()
        Log.v("RoomDbManager", list.toString())
        val user = userDao.getUserById(1)
//        user.isOnline = true
        userDao.addUser(user)
        list = userDao.listUsers()
        Log.v("RoomDbManager", list.toString())
//        list.forEach { user->
//            user.amount = 30
//            userDao.addUser(user)
//            list = userDao.listUsers()
//            Log.v("RoomDbManager", list.toString())
//        }

//        val userLocalUser1 = LocalUser(name = "Furkan", amount = 20, isOnline = true)
//        val userLocalUser2 = LocalUser(name = "Irem", amount = 20, isOnline = false)
//        userDao.addUser(userLocalUser1)
//        userDao.addUser(userLocalUser2)
//        val list2 = userDao.listUsers()
//        Log.v("RoomDbManager", list2.toString())

    }
}