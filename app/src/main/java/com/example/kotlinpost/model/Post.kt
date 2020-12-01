package com.example.kotlinpost.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("userId")
    var userId: Int = 0,
    @SerializedName("title")
    var title: String = "",
    @SerializedName("body")
    var body: String = "",
    @Expose
    var nameAuthor: String = ""
) : Parcelable