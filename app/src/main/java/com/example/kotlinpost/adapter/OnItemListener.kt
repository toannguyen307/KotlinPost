package com.example.kotlinpost.adapter

import com.example.kotlinpost.model.Post

interface OnItemListener {
    fun itemClick(post : Post)
}