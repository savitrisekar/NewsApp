package com.savitrisekar.newsapp.ui.features.home.webview

import com.savitrisekar.newsapp.base.arch.BaseRepositoryImpl
import com.savitrisekar.newsapp.data.local.datasource.NewsFavoriteDataSource
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.ui.features.home.webview.WebNewsContract
import javax.inject.Inject
class WebNewsRepository @Inject constructor(private val newsFavoriteDataSource: NewsFavoriteDataSource) :
    BaseRepositoryImpl(), WebNewsContract.Repository {
    override suspend fun insertNewsFavorite(article: Article): Long {
        return newsFavoriteDataSource.insertNewsFavorite(article)
    }

    override suspend fun deleteNewsFavorite(article: Article): Int {
        return newsFavoriteDataSource.deleteNewsFavorite(article)
    }
}