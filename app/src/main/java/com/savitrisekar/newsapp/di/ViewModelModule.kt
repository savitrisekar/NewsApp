package com.savitrisekar.newsapp.di

import com.savitrisekar.newsapp.base.arch.GenericViewModelFactory
import com.savitrisekar.newsapp.ui.features.favorite.NewsFavoriteRepository
import com.savitrisekar.newsapp.ui.features.favorite.NewsFavoriteViewModel
import com.savitrisekar.newsapp.ui.features.home.main.HomeRepository
import com.savitrisekar.newsapp.ui.features.home.main.HomeViewModel
import com.savitrisekar.newsapp.ui.features.home.webview.WebNewsRepository
import com.savitrisekar.newsapp.ui.features.home.webview.WebNewsViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ViewModelModule {

    /**news*/
    @Provides
    @ActivityScoped
    fun provideHomeViewModel(
        homeRepository: HomeRepository
    ): HomeViewModel {
        return GenericViewModelFactory(HomeViewModel(homeRepository)).create(
            HomeViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideWebNewsViewModel(webNewsRepository: WebNewsRepository): WebNewsViewModel {
        return GenericViewModelFactory(WebNewsViewModel(webNewsRepository)).create(
            WebNewsViewModel::class.java
        )
    }

    @Provides
    @ActivityScoped
    fun provideNewsFavoriteViewModel(newsFavoriteRepository: NewsFavoriteRepository): NewsFavoriteViewModel {
        return GenericViewModelFactory(NewsFavoriteViewModel(newsFavoriteRepository)).create(
            NewsFavoriteViewModel::class.java
        )
    }
}