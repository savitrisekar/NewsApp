package com.savitrisekar.newsapp.ui.features.favorite

import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.savitrisekar.newsapp.R
import com.savitrisekar.newsapp.base.arch.BaseFragment
import com.savitrisekar.newsapp.base.model.Resource
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.databinding.FragmentNewsFavoriteBinding
import com.savitrisekar.newsapp.ui.features.favorite.adapter.NewsFavoriteAdapter
import com.savitrisekar.newsapp.ui.features.home.webview.WebNewsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFavoriteFragment :
    BaseFragment<FragmentNewsFavoriteBinding, NewsFavoriteViewModel>(FragmentNewsFavoriteBinding::inflate),
    NewsFavoriteContract.View {

    private lateinit var adapter: NewsFavoriteAdapter
    private var listNewsFavorite: List<Article>? = null

    override fun initView() {
        getListData()
    }

    override fun initList() {
        adapter = NewsFavoriteAdapter {
            val intent = Intent(requireContext(), WebNewsActivity::class.java)
            intent.putExtra("article", it)
            startActivity(intent)
        }
        getViewBinding().rvFavoriteRecipe.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@NewsFavoriteFragment.adapter
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val newsFavorite = listNewsFavorite?.get(position)
                newsFavorite?.let {
                    getViewModel().deleteNewsFavorite(it)
                }
                Snackbar.make(
                    requireView(),
                    getString(R.string.label_deleted),
                    Snackbar.LENGTH_SHORT
                ).show()
                getListData()
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(getViewBinding().rvFavoriteRecipe)
        }
    }

    override fun getListData() {
        getViewModel().getAllNewsFavorite()
    }

    override fun observeData() {
        super.observeData()
        getViewModel().getNewsListLiveData().observe(this) {
            when (it) {
                is Resource.Loading -> {
                    showLoading(true)
                    showContent(false)
                    showError(false, null)
                }
                is Resource.Success -> {
                    showLoading(false)
                    showContent(true)
                    showError(false, null)

                    initList()
                    setDataAdapter(it.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    private fun setDataAdapter(data: List<Article>?) {
        data?.let {
            adapter.setItems(it)
            listNewsFavorite = it
        }
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressBar.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().rvFavoriteRecipe.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        msg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }
}