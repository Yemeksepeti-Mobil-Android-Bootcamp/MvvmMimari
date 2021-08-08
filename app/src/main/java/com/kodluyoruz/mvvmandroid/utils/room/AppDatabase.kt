package com.kodluyoruz.mvvmandroid.utils.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
