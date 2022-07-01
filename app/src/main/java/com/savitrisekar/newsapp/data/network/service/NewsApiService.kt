package com.savitrisekar.newsapp.data.network.service

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.savitrisekar.newsapp.BuildConfig
import com.savitrisekar.newsapp.data.model.NewsResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface NewsApiService {

    /**
     * list news
     * **/
    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("category") category: String,
        @Query("apiKey") key: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): NewsResponse

    //all list when searching
    @GET("everything")
    suspend fun searchNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse

    companion object {
        @JvmStatic
        operator fun invoke(chuckerInterceptor: ChuckerInterceptor): NewsApiService {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL_NEWS)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            return retrofit.create(NewsApiService::class.java)
        }
    }
}