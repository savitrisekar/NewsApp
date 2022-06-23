package com.savitrisekar.newsapp.ui.features.favorite

import com.savitrisekar.newsapp.base.arch.BaseRepositoryImpl
import com.savitrisekar.newsapp.data.local.datasource.NewsFavoriteDataSource
import com.savitrisekar.newsapp.data.model.Article
import javax.inject.Inject

class NewsFavoriteRepository @Inject constructor(private val newsFavoriteDataSource: NewsFavoriteDataSource) :
    BaseRepositoryImpl(), NewsFavoriteContract.Repository {

    override suspend fun getAllNewsFavorite(): List<Article> {
        return newsFavoriteDataSource.getAllNewsFavorite()
    }

    override suspend fun deleteNewsFavorite(article: Article): Int {
        return newsFavoriteDataSource.deleteNewsFavorite(article)
    }
}