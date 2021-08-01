package com.kodluyoruz.mvvmandroid.data.remote

import com.kodluyoruz.mvvmandroid.data.entity.login.LoginRequest
import com.kodluyoruz.mvvmandroid.data.entity.login.LoginResponse
import com.kodluyoruz.mvvmandroid.data.entity.register.RegisterRequest
import com.kodluyoruz.mvvmandroid.data.entity.register.RegisterResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface NetworkApiService {

    @POST("auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @Multipart
    @POST("api/Accounts/editaccount")
    suspend fun uploadFile(
        @Part("file\"; filename=\"pp.png\" ") file: RequestBody,
        @Part("FirstName") fname: RequestBody,
        @Part("Id") id: RequestBody
    ): Response<LoginResponse>
}

