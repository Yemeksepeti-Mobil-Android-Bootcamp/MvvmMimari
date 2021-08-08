package com.kodluyoruz.mvvmandroid.ui.splash

import android.app.Application
import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import com.kodluyoruz.mvvmandroid.data.ApiRepository
import com.kodluyoruz.mvvmandroid.utils.WorkManagerUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository

) : ViewModel() {
    private var navigationLiveData = MutableLiveData<String>()
    private var workManagerUtil: WorkManagerUtil? = null
    fun observeNavigationLiveData(): LiveData<String> = navigationLiveData

    fun workWorkInit(application: Application) {
        workManagerUtil = WorkManagerUtil(application)
        workManagerUtil?.apply()

    }

    override fun onCleared() {
        super.onCleared()
        workManagerUtil?.cancel()
    }

    fun checkTokenAndNavigation() {
        apiRepository.checkUsers()

        Firebase.messaging.getToken().addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w("PushNotification", "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            // Log and toast
            val msg = "Token: $token"
            Log.d("PushNotification", msg)
//            Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
        })
        Handler().postDelayed({
            if (!apiRepository.checkToken().isNullOrEmpty())
                navigationLiveData.value = "home"
            else {
                navigationLiveData.value = "auth"
            }
        }, 1200)
    }
}