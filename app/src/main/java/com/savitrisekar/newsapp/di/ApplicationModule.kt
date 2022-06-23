package com.savitrisekar.newsapp.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.savitrisekar.newsapp.data.local.database.FavNewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FavNewsDatabase::class.java, "news_db").build()

    /**news*/
    @Provides
    @Singleton
    fun provideNewsDao(database: FavNewsDatabase) = database.newFavoriteDao()

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}