package com.kodluyoruz.mvvmandroid.data

import com.kodluyoruz.mvvmandroid.data.remote.RemoteDataSource
import com.kodluyoruz.mvvmandroid.utils.performNetworkOperation
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private var remoteDataSource: RemoteDataSource,
//    private var localDataSource: LocalDataSource
) {

    fun getChacracterList() = performNetworkOperation {
        remoteDataSource.fetchListCharacters()
    }

}