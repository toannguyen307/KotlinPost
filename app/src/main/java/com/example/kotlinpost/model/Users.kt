package com.example.kotlinpost.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("name")
    var name: String = ""
)