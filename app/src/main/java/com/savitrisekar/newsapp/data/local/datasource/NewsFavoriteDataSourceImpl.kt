package com.savitrisekar.newsapp.data.local.datasource

import com.savitrisekar.newsapp.data.local.dao.NewsDao
import com.savitrisekar.newsapp.data.model.Article
import javax.inject.Inject

class NewsFavoriteDataSourceImpl @Inject constructor(private val dao: NewsDao) :
    NewsFavoriteDataSource {
    override suspend fun insertNewsFavorite(favNews: Article): Long {
        return dao.insertNews(favNews)
    }

    override suspend fun deleteNewsFavorite(favNews: Article): Int {
        return dao.deleteNews(favNews)
    }

    override suspend fun getAllNewsFavorite(): List<Article> {
        return dao.getFavoriteNews()
    }
}