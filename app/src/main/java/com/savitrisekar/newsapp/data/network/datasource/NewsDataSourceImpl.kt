package com.savitrisekar.newsapp.data.network.datasource

import com.savitrisekar.newsapp.data.model.NewsResponse
import com.savitrisekar.newsapp.data.network.service.NewsApiService
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(private val recipeApiService: NewsApiService) :
    NewsDataSource {
    override suspend fun getAllRecipes(category: String): NewsResponse {
        return recipeApiService.getAllRecipes(category)
    }

    override suspend fun searchNews(query: String): NewsResponse {
        return recipeApiService.searchNews(query)
    }
}