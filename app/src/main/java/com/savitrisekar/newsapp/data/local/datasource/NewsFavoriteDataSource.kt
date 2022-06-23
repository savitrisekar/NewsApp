package com.savitrisekar.newsapp.data.local.datasource

import com.savitrisekar.newsapp.data.model.Article


interface NewsFavoriteDataSource {

    suspend fun insertNewsFavorite(favNews: Article): Long

    suspend fun deleteNewsFavorite(favNews: Article): Int

    suspend fun getAllNewsFavorite(): List<Article>
}