package com.kodluyoruz.mvvmandroid.data.remote

import com.kodluyoruz.mvvmandroid.utils.BaseDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: NetworkApiService) :
    BaseDataSource() {

    suspend fun fetchListCharacters(page: Int = 1) = getResult { apiService.listCharacters(page) }

    suspend fun getChacter(id: Int) = getResult { apiService.getCharacter(id) }
}