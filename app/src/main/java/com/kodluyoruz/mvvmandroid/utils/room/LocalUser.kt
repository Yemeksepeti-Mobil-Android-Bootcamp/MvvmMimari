package com.kodluyoruz.mvvmandroid.utils.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalUser(
    @PrimaryKey(autoGenerate = true) var userId: Int = 0,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "amount") var email: String?,
    @ColumnInfo(name = "role") var role: String?,
)

