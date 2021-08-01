package com.kodluyoruz.mvvmandroid.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kodluyoruz.mvvmandroid.data.ApiRepository
import com.kodluyoruz.mvvmandroid.data.entity.register.RegisterRequest
import com.kodluyoruz.mvvmandroid.data.entity.register.RegisterResponse
import com.kodluyoruz.mvvmandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    val apiRepository: ApiRepository
) : ViewModel() {

    fun register(
        email: String,
        name: String,
        password: String
    ): LiveData<Resource<RegisterResponse>> {
        val request = RegisterRequest(email, name, password)
        return apiRepository.register(request)
    }
}