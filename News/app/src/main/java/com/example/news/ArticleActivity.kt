package com.example.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

private const val TAG = "ArticleActivity"


class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val description = intent.getStringExtra("ARTICLE")
        Log.i(TAG, "Description: $description")
    }
}