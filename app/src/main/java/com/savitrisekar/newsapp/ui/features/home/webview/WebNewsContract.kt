package com.savitrisekar.newsapp.ui.features.home.webview

import com.savitrisekar.newsapp.base.arch.BaseContract
import com.savitrisekar.newsapp.data.model.Article

interface WebNewsContract {
    interface View : BaseContract.BaseView {
        fun showWebNews(url: String)
    }

    interface ViewModel : BaseContract.BaseViewModel {
        fun insertNewsFavorite(article: Article)
    }

    interface Repository : BaseContract.BaseRepository {
        suspend fun insertNewsFavorite(article: Article): Long
        suspend fun deleteNewsFavorite(article: Article): Int
    }
}