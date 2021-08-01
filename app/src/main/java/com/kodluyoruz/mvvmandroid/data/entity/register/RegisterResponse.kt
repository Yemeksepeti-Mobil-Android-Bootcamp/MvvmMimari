package com.kodluyoruz.mvvmandroid.data.entity.register


import com.google.gson.annotations.SerializedName
import com.kodluyoruz.mvvmandroid.data.entity.common.Data

data class RegisterResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("token")
    val token: String
)