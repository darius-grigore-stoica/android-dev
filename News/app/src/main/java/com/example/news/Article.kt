package com.example.news

import com.google.gson.annotations.SerializedName

data class Response(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val source : Source,
    val author: String,
    val title: String,
    val description: String,
    val urlToImage: String,
    @SerializedName("publishedAt") val date: String,
)

data class Source(
    val id: String,
    val name: String
)
