package com.example.kotlinpost.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlinpost.R
import com.example.kotlinpost.model.Post
import kotlinx.android.synthetic.main.fragment_detail_post.*

class DetailPostFragment : Fragment() {

    var post: Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            post = it.getParcelable("post")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_post, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        post?.let {
            tvId.text = "ID: ${it.id}"
            tvBody.text = it.body
            tvTitle.text = it.title
            tvUserId.text = "User ID: ${it.userId}"
            tvNameAuthor.text = "Name Author: ${it.nameAuthor}"
        }

    }
}