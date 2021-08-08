package com.kodluyoruz.mvvmandroid.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kodluyoruz.mvvmandroid.R
import com.kodluyoruz.mvvmandroid.data.remote.NetworkApiService
import com.kodluyoruz.mvvmandroid.utils.AuthStateListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AuthStateListener {

    @Inject
    lateinit var service: NetworkApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("MainActivity", "$service")
//        RoomDbManager.initialize(this)
    }

    override fun logout() {
        startActivity(Intent(this, MainActivity::class.java))
        this.finish()
    }

    override fun navigateUpTo(upIntent: Intent?): Boolean {
        return super.navigateUpTo(upIntent)
    }
}