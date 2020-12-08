package com.example.kotlinpost.presenter

import com.example.kotlinpost.Contract
import com.example.kotlinpost.model.Post
import com.example.kotlinpost.model.Users
import com.example.kotlinpost.retrofit.APIManager
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import java.util.*
import kotlin.Exception

class PostPresenter(private val iView: Contract.IView) : Contract.IPresenter {
    override fun getData() {
        iView.showLoading()
        Single.zip(getPostObservable(), getUsersObservable(),
            BiFunction { postsList, usersList ->
                return@BiFunction filterNameAuthorPost(
                    postsList,
                    usersList
                )
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getObserver())
    }

    private fun getUsersObservable(): Single<List<Users>> = APIManager.requestAPI.listUser()

    private fun getPostObservable(): Single<List<Post>> = APIManager.requestAPI.listPost()

    private fun filterNameAuthorPost(posts: List<Post>, users: List<Users>): List<Post> {
        posts.forEach { post ->
            post.nameAuthor = users?.firstOrNull { it.id == post.userId }?.name.toString()
        }
        return posts
    }

    private fun getObserver(): SingleObserver<List<Post>> = object : SingleObserver<List<Post>> {
        override fun onSuccess(value: List<Post>?) {
            iView.showHideLoading()
            iView.showListData(value)
        }

        override fun onSubscribe(d: Disposable?) {
        }

        override fun onError(e: Throwable?) {
            iView.showHideLoading()
            iView.showError(Exception(e))
        }
    }
}