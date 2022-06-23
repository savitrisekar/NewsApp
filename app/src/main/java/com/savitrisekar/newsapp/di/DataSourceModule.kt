package com.savitrisekar.newsapp.di

import com.savitrisekar.newsapp.data.local.datasource.NewsFavoriteDataSourceImpl
import com.savitrisekar.newsapp.data.local.dao.NewsDao
import com.savitrisekar.newsapp.data.local.datasource.NewsFavoriteDataSource
import com.savitrisekar.newsapp.data.network.datasource.NewsDataSource
import com.savitrisekar.newsapp.data.network.datasource.NewsDataSourceImpl
import com.savitrisekar.newsapp.data.network.service.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRecipeDataSource(newsApiService: NewsApiService): NewsDataSource {
        return NewsDataSourceImpl(newsApiService)
    }

    /**news*/
    @Singleton
    @Provides
    fun provideNewsFavoriteDataSource(newsDao: NewsDao): NewsFavoriteDataSource {
        return NewsFavoriteDataSourceImpl(newsDao)
    }
}