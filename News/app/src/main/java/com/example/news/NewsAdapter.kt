package com.example.news

import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

class NewsAdapter(private val context: Context, private val articles: List<Article>, private val btnClickListener: ButtonClickListener) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    interface ButtonClickListener {
        fun onButtonClicked(position: Int)
    }

    override fun getItemCount(): Int = articles.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val tvAuthor = itemView.findViewById<TextView>(R.id.tvAuthor)
        val tvDate = itemView.findViewById<TextView>(R.id.tvDate)
        val tvSource = itemView.findViewById<TextView>(R.id.tvSource)
        val image = itemView.findViewById<ImageView>(R.id.imageView)
        val btnReadMore = itemView.findViewById<Button>(R.id.btnReadMore)
        fun bind(position: Int) {
            val article = articles[position]
            if(isValidArticle(article)) {
                tvTitle.text = article.title
                tvAuthor.text = article.author
                tvDate.text = article.date
                tvDescription.text = article.description
                tvSource.text = article.source.name
                Glide.with(context).load(article.urlToImage).apply(
                    RequestOptions().transform(
                        CenterCrop(), RoundedCorners(20)
                    )
                ).into(image)
                btnReadMore.setOnClickListener{
                    btnClickListener.onButtonClicked(position)
                }
            }
        }

        private fun isValidArticle(article: Article): Boolean {
            return !(article.urlToImage.isEmpty()
                    && article.title.isEmpty()
                    && article.source.name.isEmpty()
                    && article.date.isEmpty()
                    && article.author.isEmpty()
                    && article.description.isEmpty())
        }
    }

}
