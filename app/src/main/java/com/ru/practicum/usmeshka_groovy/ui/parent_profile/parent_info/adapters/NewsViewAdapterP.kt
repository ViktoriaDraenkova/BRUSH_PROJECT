package com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.NewsSmallBinding
import com.ru.practicum.usmeshka_groovy.domain.models.News
import com.ru.practicum.usmeshka_groovy.util.dpToPx

class NewsViewAdapterP(private val clickListener: ViewHolder.OnClickListener) :
    RecyclerView.Adapter<NewsViewAdapterP.ViewHolder>() {
    private var news = mutableListOf<News>()

    fun setList(list: List<News>) {
        this.news.clear()
        this.news.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NewsSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = news.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(news[position])
    }

    class ViewHolder(
        private val binding: NewsSmallBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            Log.d("Rendering new", news.toString())
            val requestOptions =
                RequestOptions().transform(RoundedCorners(2.dpToPx(itemView.context)))
            binding.newsName.text = news.name
            binding.newsDate.text = news.date
            Glide.with(itemView).load(news.newsImg).placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .apply(requestOptions).into(binding.newsPreview)
            itemView.setOnClickListener { clickListener.onArticleClick(news) }
        }

        fun interface OnClickListener {
            fun onArticleClick(news: News)
        }
    }
}