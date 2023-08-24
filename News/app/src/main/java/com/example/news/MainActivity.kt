package com.example.news

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://newsapi.org/"
private const val API_KEY = "767a5531c1d84af395f0ab4eea1ae06c"
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rvArticles)

        val articles = mutableListOf<Article>()
        val adapter = NewsAdapter(this, articles, object : NewsAdapter.ButtonClickListener{
            override fun onButtonClicked(position: Int) {
                val article = articles[position]
                Log.i(TAG, "Opening URL: ${article.url}")
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
                startActivity(intent)
            }
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val newsService = retrofit.create(NewsAPI::class.java)
            .getBreakingNews("us", API_KEY)
            .enqueue(object : Callback<com.example.news.Response> {
                override fun onResponse(call: Call<com.example.news.Response>, response: Response<com.example.news.Response>) {
                    Log.i(TAG, "onResponse $response")
                    val body = response.body()
                    if(body == null) {
                        Log.w(TAG, "Body null")
                        return
                    }
                    articles.addAll(body.articles)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<com.example.news.Response>, t: Throwable) {
                    Log.i(TAG, "onFailure $t")

                }
            })
    }
}
