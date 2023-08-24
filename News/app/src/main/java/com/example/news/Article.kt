package com.example.news

import android.os.Parcel
import android.os.Parcelable
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
    val url: String,
)


data class Source(
    val id: String,
    val name: String
)
