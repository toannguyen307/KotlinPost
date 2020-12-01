package com.example.kotlinpost.view

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context
import android.icu.util.ValueIterator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kotlinpost.Contract
import com.example.kotlinpost.R
import com.example.kotlinpost.R.color.purple_700
import com.example.kotlinpost.adapter.OnItemListener
import com.example.kotlinpost.adapter.PostAdapter
import com.example.kotlinpost.model.Post
import com.example.kotlinpost.presenter.PostPresenter
import kotlinx.android.synthetic.main.fragment_post.*

class PostFragment : Fragment(), Contract.IView, OnItemListener {
    var mPosts: MutableList<Post> = mutableListOf()
    private var postPresenter: PostPresenter= PostPresenter(this)
    private lateinit var adapters: PostAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onStart() {
        super.onStart()
        postPresenter?.run {
            getData()
        }
    }

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh)
        swipeRefreshLayout.setColorSchemeColors(purple_700, R.color.custom)
        swipeRefreshLayout.setOnRefreshListener {
            Handler().postDelayed({
                postPresenter?.run {
                    getData()
                }
            }, 1000)
        }
        adapters = PostAdapter(mPosts, this)

        rvPost?.also {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(context)
            it.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            it.adapter = adapters
        }
    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun showError(e: Exception?) {
        Toast.makeText(context,"Internet connection error",Toast.LENGTH_LONG).show()
    }

    override fun showHideLoading() {
        swipeRefreshLayout!!.isRefreshing = false
        progressBar.visibility = View.GONE
    }

    override fun showListData(postList: List<Post>?) {
        mPosts.clear()
        postList?.let { mPosts.addAll(it) }
        adapters.notifyDataSetChanged()
    }

    override fun itemClick(post: Post) {
        val detailPostFragment = DetailPostFragment()
        val bundle = Bundle()
        bundle.run {
            putParcelable("post", post)
        }
        detailPostFragment.arguments = bundle
        activity!!.supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                detailPostFragment,
                DetailPostFragment::class.java.simpleName
            )
            .addToBackStack(null)
            .commit()
    }


}