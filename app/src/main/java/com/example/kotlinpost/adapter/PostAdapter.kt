package com.example.kotlinpost.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpost.R
import com.example.kotlinpost.model.Post

class PostAdapter(private val posts: List<Post>, private val onItemListener: OnItemListener) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.post_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle.text = "Title : ${posts[position].title}"
        holder.tvNameAuthor.text = "Name : ${posts[position].nameAuthor}"
        holder.line.setOnClickListener { onItemListener.itemClick(posts[position]) }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val tvNameAuthor: TextView = itemView.findViewById(R.id.tvNameAuthor)
        val line: LinearLayout = itemView.findViewById(R.id.line)
    }


}