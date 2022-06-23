package com.savitrisekar.newsapp.ui.features.home.main

import androidx.lifecycle.LiveData
import com.savitrisekar.newsapp.base.arch.BaseContract
import com.savitrisekar.newsapp.base.model.Resource
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.data.model.NewsResponse

interface HomeListContract {
    interface View : BaseContract.BaseView {
        fun initList()
        fun getData()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getRecipeListLiveData(): LiveData<Resource<List<Article>>>
        fun getAllRecipes(category: String)
        fun searchNews(query: String)
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllRecipes(category: String): NewsResponse
        suspend fun searchNews(query: String): NewsResponse
    }
}