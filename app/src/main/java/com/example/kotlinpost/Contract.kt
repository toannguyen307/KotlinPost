package com.example.kotlinpost

import com.example.kotlinpost.model.Post

interface Contract {
    interface IPresenter{
        fun getData()
    }

    interface IView{
        fun showLoading()
        fun showError(e: Exception?)
        fun showHideLoading()
        fun showListData(postList: List<Post>?)
    }

}