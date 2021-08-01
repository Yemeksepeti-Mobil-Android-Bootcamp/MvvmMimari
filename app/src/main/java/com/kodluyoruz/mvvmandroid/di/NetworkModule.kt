package com.kodluyoruz.mvvmandroid.di

import androidx.viewbinding.BuildConfig
import com.google.gson.Gson
import com.kodluyoruz.mvvmandroid.data.remote.NetworkApiService
import com.kodluyoruz.mvvmandroid.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): NetworkApiService {
        return retrofit.create(NetworkApiService::class.java)
    }

    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gson: Gson,
        endPoint: EndPoint
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(endPoint.url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.interceptors().add(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        return builder.build()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @TunahanEndPoint
    fun provideApiString(): String {
        return "https://dist-learn.herokuapp.com/api/"
    }

    @Provides
    fun provideRemoteDataSource(
        apiService: NetworkApiService,
//        rickApiService: RickApiService
    ): RemoteDataSource {
        return RemoteDataSource(apiService)
    }
//    @Provides
//    fun provideRickApiService(retrofit: Retrofit): RickApiService {
//        return retrofit.create(RickApiService::class.java)
//    }

//    @Provides
//    @RickAndMortRetrofit
//    fun provideRickRetrofit(
//        okHttpClient: OkHttpClient,
//        gson: Gson,
//        @RickAndMortyEndPoint endPoint: String
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(endPoint)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(okHttpClient)
//            .build()
//    }


//    @Provides
//    fun provideAuthRetrofit(
//        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient,
//        gson: Gson,
//        endPoint: EndPoint
//    ): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(endPoint.url)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .client(okHttpClient)
//            .build()
//    }


//    @AuthInterceptorOkHttpClient
//    @Provides
//    fun provideAuthInterceptorOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor {
//                val request = it.request().newBuilder().addHeader("X-Token", "token").build()
//                it.proceed(request)
//            }
//            .build()
//    }



    @Provides
    fun provideEndPoint(): EndPoint {
        return EndPoint("https://dist-learn.herokuapp.com/api/")
    }



//    @Provides
//    @RickAndMortyEndPoint
//    fun provideRickString(): String {
//        return "https://rickandmortyapi.com/api/"
//    }





}

data class EndPoint(val url: String)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TunahanEndPoint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RickAndMortyEndPoint

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RickAndMortRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class TunahanRetrofit
