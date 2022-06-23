package com.savitrisekar.newsapp.ui.features.home.main

import android.content.Intent
import android.util.Log
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.savitrisekar.newsapp.ui.features.home.main.adapter.HomeAdapter
import com.savitrisekar.newsapp.base.arch.BaseFragment
import com.savitrisekar.newsapp.base.model.Resource
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.data.model.Category
import com.savitrisekar.newsapp.databinding.FragmentHomeBinding
import com.savitrisekar.newsapp.ui.features.home.main.adapter.CategoryAdapter
import com.savitrisekar.newsapp.ui.features.home.webview.WebNewsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeViewModel>(FragmentHomeBinding::inflate),
    HomeListContract.View {

    private lateinit var adapter: HomeAdapter
    private lateinit var categoryAdapter: CategoryAdapter
    private var category: String = "business"

    override fun initView() {
        getViewBinding().edtSearch.addTextChangedListener { query ->
            query?.let {
                if (query.toString().isNotEmpty()) {
                    getSearchNews(query.toString())
                }
            }
        }

        getData()
    }

    private fun getSearchNews(query: String) {
        val search = "%$query%"
        getViewModel().searchNews(search)
    }

    override fun getData() {
        getViewModel().getAllRecipes(category)
        Log.d("check", category)
    }

    override fun showLoading(isVisible: Boolean) {
        getViewBinding().progressCategory.isVisible = isVisible
    }

    override fun showContent(isVisible: Boolean) {
        getViewBinding().rvNews.isVisible = isVisible
    }

    override fun showError(isErrorEnabled: Boolean, msg: String?) {
        getViewBinding().tvError.isVisible = isErrorEnabled
        getViewBinding().tvError.text = msg
    }

    override fun observeData() {
        getViewModel().getRecipeListLiveData().observe(this) {
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
                    setNewsAdapter(it.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    showContent(false)
                    showError(true, it.message)
                }
            }
        }
    }

    override fun initList() {
        /**category*/
        categoryAdapter = CategoryAdapter {
            when (it.name) {

                "Business" -> category = "business"
                "Entertainment" -> category = "entertainment"
                "General" -> category = "general"
                "Health" -> category = "health"
                "Science" -> category = "science"
                "Sports" -> category = "sports"
                "Technology" -> category = "technology"
            }
        }

        setCategoryAdapter()

        getViewBinding().rvCategory.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = this@HomeFragment.categoryAdapter
        }

        /**home*/
        adapter = HomeAdapter {
            val intent = Intent(requireContext(), WebNewsActivity::class.java)
            intent.putExtra("article", it)
            startActivity(intent)
        }
        getViewBinding().rvNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
    }

    private fun setNewsAdapter(data: List<Article>?) {
        data?.let { adapter.setItems(it) }
    }

    private fun setCategoryAdapter() {
        categoryAdapter.setItems(categoryList)
        Log.d("category_list", categoryList.toString())
    }

    private val categoryList = listOf<Category>(
        Category(1, "Business"),
        Category(2, "Entertainment"),
        Category(3, "General"),
        Category(4, "Health"),
        Category(5, "Science"),
        Category(6, "Sports"),
        Category(7, "Technology")
    )
}