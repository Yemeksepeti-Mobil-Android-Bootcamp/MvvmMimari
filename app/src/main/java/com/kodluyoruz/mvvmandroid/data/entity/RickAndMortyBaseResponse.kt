package com.kodluyoruz.mvvmandroid.data.entity

import com.google.gson.annotations.SerializedName

data class RickAndMortyBaseResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("results")
    val characters: List<Character>
)