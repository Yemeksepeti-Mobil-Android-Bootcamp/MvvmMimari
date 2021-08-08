package com.kodluyoruz.mvvmandroid.di

import android.content.Context
import androidx.room.Room
import com.kodluyoruz.mvvmandroid.data.local.LocalDataSource
import com.kodluyoruz.mvvmandroid.data.local.SharedPrefManager
import com.kodluyoruz.mvvmandroid.utils.room.AppDatabase
import com.kodluyoruz.mvvmandroid.utils.room.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(
    ActivityRetainedComponent::class
)
class DatabaseModule {

    @Provides
    fun sharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }

    @Provides
    fun localDataSource(sharedPrefManager: SharedPrefManager, userDao: UserDao): LocalDataSource {
        return LocalDataSource(sharedPrefManager, userDao)
    }

    @Provides
    fun provideRoomDb(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "LocalDb")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }
}