package com.savitrisekar.newsapp.ui.features.favorite

import androidx.lifecycle.LiveData
import com.savitrisekar.newsapp.base.arch.BaseContract
import com.savitrisekar.newsapp.base.model.Resource
import com.savitrisekar.newsapp.data.model.Article

interface NewsFavoriteContract {
    interface View : BaseContract.BaseView {
        fun initList()
        fun getListData()
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun getNewsListLiveData(): LiveData<Resource<List<Article>>>
        fun getAllNewsFavorite()
        fun deleteNewsFavorite(article: Article)
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun getAllNewsFavorite(): List<Article>
        suspend fun deleteNewsFavorite(article: Article): Int
    }
}