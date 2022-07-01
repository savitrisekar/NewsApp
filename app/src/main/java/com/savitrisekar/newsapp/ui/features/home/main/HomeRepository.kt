package com.savitrisekar.newsapp.ui.features.home.main

import com.savitrisekar.newsapp.base.arch.BaseRepositoryImpl
import com.savitrisekar.newsapp.data.model.NewsResponse
import com.savitrisekar.newsapp.data.network.datasource.NewsDataSource
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val datasource: NewsDataSource
) :
    BaseRepositoryImpl(), HomeListContract.Repository {

    override suspend fun getAllNews(category: String): NewsResponse =
        datasource.getAllNews(category)

    override suspend fun searchNews(query: String): NewsResponse {
        return datasource.searchNews(query)
    }
}