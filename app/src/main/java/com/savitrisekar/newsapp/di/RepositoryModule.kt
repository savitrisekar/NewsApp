package com.savitrisekar.newsapp.di

import com.savitrisekar.newsapp.data.local.datasource.NewsFavoriteDataSource
import com.savitrisekar.newsapp.data.network.datasource.NewsDataSource
import com.savitrisekar.newsapp.ui.features.favorite.NewsFavoriteRepository
import com.savitrisekar.newsapp.ui.features.home.main.HomeRepository
import com.savitrisekar.newsapp.ui.features.home.webview.WebNewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**news*/
    @Provides
    @Singleton
    fun provideHomeRepository(
        newsDataSource: NewsDataSource
    ): HomeRepository {
        return HomeRepository(newsDataSource)
    }

    @Provides
    @Singleton
    fun provideWebNewsRepository(
        newsFavoriteDataSource: NewsFavoriteDataSource
    ) : WebNewsRepository {
        return WebNewsRepository(newsFavoriteDataSource)
    }

    @Singleton
    @Provides
    fun provideNewsFavoriteRepository(
        newsFavoriteDataSource: NewsFavoriteDataSource
    ): NewsFavoriteRepository {
        return NewsFavoriteRepository(newsFavoriteDataSource)
    }
}