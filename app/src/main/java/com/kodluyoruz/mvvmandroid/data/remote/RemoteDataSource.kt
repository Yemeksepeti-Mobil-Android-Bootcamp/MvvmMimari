package com.kodluyoruz.mvvmandroid.data.remote

import com.kodluyoruz.mvvmandroid.data.entity.login.LoginRequest
import com.kodluyoruz.mvvmandroid.data.entity.register.RegisterRequest
import com.kodluyoruz.mvvmandroid.utils.BaseDataSource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: NetworkApiService,
//    private val rickApi: RickApiService
) :
    BaseDataSource() {

//    suspend fun fetchListCharacters(page: Int = 1) = getResult { rickApi.listCharacters(page) }
//
//    suspend fun getCharacter(id: Int) = getResult { rickApi.getCharacter(id) }

    suspend fun postRegister(request: RegisterRequest) = getResult { apiService.register(request) }

    suspend fun postLogin(request: LoginRequest) = getResult {
        apiService.login(request)
    }

    //Upload file and multipart example
    suspend fun uploadFile(file: File) =
        getResult {
            apiService.uploadFile(
                file.asRequestBody("image/*".toMediaTypeOrNull()),
                "Hello Name".toRequestBody("text/plain".toMediaTypeOrNull()),
                "Hello Name".toRequestBody("text/plain".toMediaTypeOrNull())
            )
        }
}