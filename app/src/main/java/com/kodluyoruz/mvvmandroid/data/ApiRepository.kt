package com.kodluyoruz.mvvmandroid.data

import com.kodluyoruz.mvvmandroid.data.entity.login.LoginRequest
import com.kodluyoruz.mvvmandroid.data.entity.register.RegisterRequest
import com.kodluyoruz.mvvmandroid.data.local.LocalDataSource
import com.kodluyoruz.mvvmandroid.data.remote.RemoteDataSource
import com.kodluyoruz.mvvmandroid.utils.performAuthTokenNetworkOperation
import com.kodluyoruz.mvvmandroid.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: LocalDataSource
) {
// Rick and morty character list endpoint.
//    fun getChacracterList() = performNetworkOperation {
//        remoteDataSource.fetchListCharacters()
//    }

    fun register(registerRequest: RegisterRequest) =
        performNetworkOperation {
            remoteDataSource.postRegister(request = registerRequest)
        }

//    fun login(request: LoginRequest) =
//        performNetworkOperation { remoteDataSource.postLogin(request) }

    fun login(request: LoginRequest) = performAuthTokenNetworkOperation(
        call = {
            remoteDataSource.postLogin(request)
        },
        saveToken = {
            localDataSource.saveToken(it)
        }
    )

    fun checkToken(): String? {
        val token = localDataSource.getToken()
        return token
    }

}