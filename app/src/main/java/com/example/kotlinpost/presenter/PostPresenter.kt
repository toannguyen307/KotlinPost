package com.example.kotlinpost.presenter

import com.example.kotlinpost.Contract
import com.example.kotlinpost.model.Post
import com.example.kotlinpost.model.Users
import com.example.kotlinpost.retrofit.APIManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class PostPresenter(private val iView: Contract.IView) : Contract.IPresenter {
    override fun getData() {
        iView.showLoading()
        APIManager.requestAPI.listPost().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val postList: List<Post>? = response.body()
                APIManager.requestAPI.listUser().enqueue(object : Callback<List<Users>> {
                    override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                        val usersList : List<Users>? = response.body()
                        iView.showHideLoading()
                        postList?.let {
                            for (post in postList){
                                post.nameAuthor= usersList?.find { it.id==post.userId}?.name.toString()
                            }
                        }
                        iView.showListData(postList)
                    }

                    override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                        iView.showHideLoading()
                        iView.showError(Exception(t))
                    }
                })
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                iView.showHideLoading()
                iView.showError(Exception(t))

            }
        })
    }
}