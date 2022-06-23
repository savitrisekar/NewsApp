package com.savitrisekar.newsapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.savitrisekar.newsapp.data.model.Article
import com.savitrisekar.newsapp.data.local.dao.NewsDao

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class FavNewsDatabase : RoomDatabase() {

    abstract fun newFavoriteDao(): NewsDao
}