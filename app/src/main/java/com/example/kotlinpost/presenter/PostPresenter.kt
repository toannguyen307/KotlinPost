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
            .subscribe({ result ->
                iView.showHideLoading()
                iView.showListData(result)
            },
                { error ->
                    iView.showHideLoading()
                    iView.showError(Exception(error))
                }
            )
    }

    private fun getUsersObservable(): Single<List<Users>> = APIManager.requestAPI.listUser()

    private fun getPostObservable(): Single<List<Post>> = APIManager.requestAPI.listPost()

    private fun filterNameAuthorPost(postsList: List<Post>, usersList: List<Users>): List<Post> {
        postsList.forEach { post ->
            post.nameAuthor = usersList?.firstOrNull { it.id == post.userId }?.name.toString()
        }
        return postsList
    }

}