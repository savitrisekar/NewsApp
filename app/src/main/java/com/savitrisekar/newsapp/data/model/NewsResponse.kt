package com.savitrisekar.newsapp.data.model

import com.savitrisekar.newsapp.data.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)