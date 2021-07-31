package com.kodluyoruz.mvvmandroid.data.remote

import com.kodluyoruz.mvvmandroid.data.entity.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApiService {
    @GET("character")
    suspend fun listCharacters(@Query("page") page: Int): Response<RickAndMortyBaseResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>
}

