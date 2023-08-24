package com.example.news

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface NewsAPI {
    @GET("v2/everything")
    fun getAllNews(
        @Query("q") q: String,
        @Query("page") pageNum: Int,
        @Query("apiKey") API_KEY: String
    ) : Call<Response>

    @GET("v2/top-headlines")
    fun getBreakingNews(
        @Query("country") country: String,
        @Query("apiKey") API_KEY: String
    ) : Call<Response>
}