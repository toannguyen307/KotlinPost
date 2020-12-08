package com.example.kotlinpost.retrofit

import com.example.kotlinpost.model.Post
import com.example.kotlinpost.model.Users
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET

interface APIService {
    @GET("/posts")
    fun listPost() : Single<List<Post>>
    @GET("/users")
    fun listUser(): Single<List<Users>>
}