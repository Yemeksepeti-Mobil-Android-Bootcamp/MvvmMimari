package com.kodluyoruz.mvvmandroid.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kodluyoruz.mvvmandroid.data.ApiRepository
import com.kodluyoruz.mvvmandroid.data.entity.login.LoginRequest
import com.kodluyoruz.mvvmandroid.data.entity.login.LoginResponse
import com.kodluyoruz.mvvmandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    var apiRepository: ApiRepository
) : ViewModel() {

    fun login(email: String, password: String): LiveData<Resource<LoginResponse>> {
        val request = LoginRequest(email, password)
        return apiRepository.login(request)
    }
}