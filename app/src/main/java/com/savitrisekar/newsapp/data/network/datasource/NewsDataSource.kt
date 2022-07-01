package com.savitrisekar.newsapp.data.network.datasource

import com.savitrisekar.newsapp.data.model.NewsResponse

interface NewsDataSource {
    suspend fun getAllNews(category: String): NewsResponse

    suspend fun searchNews(query: String): NewsResponse
}