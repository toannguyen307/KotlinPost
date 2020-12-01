package com.example.kotlinpost.retrofit

import com.example.kotlinpost.model.Post
import com.example.kotlinpost.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("/posts")
    fun listPost() : Call<List<Post>>
    @GET("/users")
    fun listUser(): Call<List<Users>>

}