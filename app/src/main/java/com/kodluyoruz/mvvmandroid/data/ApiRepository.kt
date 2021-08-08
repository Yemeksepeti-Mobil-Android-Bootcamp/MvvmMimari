package com.kodluyoruz.mvvmandroid.data

import androidx.lifecycle.LiveData
import com.kodluyoruz.mvvmandroid.data.entity.login.LoginRequest
import com.kodluyoruz.mvvmandroid.data.entity.register.RegisterRequest
import com.kodluyoruz.mvvmandroid.data.local.LocalDataSource
import com.kodluyoruz.mvvmandroid.data.remote.RemoteDataSource
import com.kodluyoruz.mvvmandroid.utils.performAuthTokenNetworkOperation
import com.kodluyoruz.mvvmandroid.utils.performNetworkOperation
import com.kodluyoruz.mvvmandroid.utils.room.LocalUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
        },
        saveUser = {
            val user = it.toLocalUser()
            localDataSource.addUser(user)
        }
    )

    fun checkToken(): String? {
        return localDataSource.getToken()
    }

    fun listUsers():List<LocalUser> {
        return localDataSource.listUsers()
    }

//    val listUsers: Flow<List<LocalUser>> = localDataSource.listUsers().collect()

    fun checkUsers() {
//        var list = localDataSource.listUsers()
//        Log.v("RoomDbManager", "ApiRepository: $list")
//        var userLocal = LocalUser(name = "Furkan", amount = 20, isOnline = false, onlineStatus = OnlineStatus(isOnline= true))
//        localDataSource.addUser(userLocal)
//        list.forEach { user->
//            user.amount = Random.nextInt(20) * user.amount!!
//            user.isOnline = user.isOnline?.not() ?: true
//            localDataSource.addUser(user)
//        }
//        list = localDataSource.listUsers()
//        Log.v("RoomDbManager", "ApiRepository: $list")
    }

    fun removeToken() {
        localDataSource.saveToken("")
    }

    fun removeUsers(localUser: LocalUser) {
        localDataSource.removeUser(localUser)
    }

}