package com.kodluyoruz.mvvmandroid.data.entity.login


import com.google.gson.annotations.SerializedName
import com.kodluyoruz.mvvmandroid.data.entity.common.Data

data class LoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)