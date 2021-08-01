package com.kodluyoruz.mvvmandroid.ui.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kodluyoruz.mvvmandroid.data.ApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository

) : ViewModel() {
    private var navigationLiveData = MutableLiveData<String>()
    fun observeNavigationLiveData(): LiveData<String> = navigationLiveData

    fun checkTokenAndNavigation() {
        Handler().postDelayed({
            if (!apiRepository.checkToken().isNullOrEmpty())
                navigationLiveData.value = "home"
            else {
                navigationLiveData.value = "auth"
            }
        }, 1200)
    }
}