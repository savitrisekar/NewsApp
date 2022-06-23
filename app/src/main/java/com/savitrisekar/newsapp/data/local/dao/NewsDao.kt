package com.savitrisekar.newsapp.data.local.dao

import androidx.room.*
import com.savitrisekar.newsapp.data.model.Article

@Dao
interface NewsDao {
    @Query("SELECT * FROM articleEntity")
    fun getFavoriteNews(): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(articleModel: Article): Long

    @Delete
    suspend fun deleteNews(articleModel: Article): Int
}