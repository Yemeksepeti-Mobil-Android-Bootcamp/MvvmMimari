package com.kodluyoruz.mvvmandroid.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.kodluyoruz.mvvmandroid.data.ApiRepository
import com.kodluyoruz.mvvmandroid.utils.room.LocalUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    var apiRepository: ApiRepository
) : ViewModel() {
    private var userList = MutableLiveData<List<LocalUser>>()
    fun getUserListLiveData(): LiveData<List<LocalUser>> = userList

//    fun fetchRickMortList(): LiveData<Resource<RickAndMortyBaseResponse>> =
//        apiRepository.getChacracterList()

    fun listUser() {
        userList.value = apiRepository.listUsers()
    }

    fun removeToken() {
        removeList()
        apiRepository.removeToken()
    }

    fun removeList() {
        apiRepository.listUsers().forEach {
            apiRepository.removeUsers(it)
        }
    }

}