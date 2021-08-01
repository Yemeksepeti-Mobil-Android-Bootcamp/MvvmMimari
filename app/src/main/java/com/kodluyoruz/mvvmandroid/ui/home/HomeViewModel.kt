package com.kodluyoruz.mvvmandroid.ui.home

import androidx.lifecycle.*
import com.kodluyoruz.mvvmandroid.data.ApiRepository
import com.kodluyoruz.mvvmandroid.data.entity.RickAndMortyBaseResponse
import com.kodluyoruz.mvvmandroid.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var savedStateHandle: SavedStateHandle,
    var apiRepository: ApiRepository
) : ViewModel() {

//    fun fetchRickMortList(): LiveData<Resource<RickAndMortyBaseResponse>> =
//        apiRepository.getChacracterList()


}