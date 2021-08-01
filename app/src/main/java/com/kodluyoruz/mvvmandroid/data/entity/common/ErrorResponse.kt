package com.kodluyoruz.mvvmandroid.data.entity.common


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)