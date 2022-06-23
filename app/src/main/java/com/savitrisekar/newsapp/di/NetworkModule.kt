package com.savitrisekar.newsapp.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.savitrisekar.newsapp.data.network.service.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideNewsApiService(chuckerInterceptor: ChuckerInterceptor): NewsApiService {
        return NewsApiService.invoke(chuckerInterceptor)
    }

    @Singleton
    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context).build()
    }
}