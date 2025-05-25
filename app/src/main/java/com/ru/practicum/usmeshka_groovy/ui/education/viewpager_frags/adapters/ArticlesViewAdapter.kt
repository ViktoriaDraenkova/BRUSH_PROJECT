package com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.ArticlesSmallFragmentBinding
import com.ru.practicum.usmeshka_groovy.domain.models.Article
import com.ru.practicum.usmeshka_groovy.util.dpToPx

class ArticlesViewAdapter(private val clickListener: ViewHolder.OnClickListener) :
    RecyclerView.Adapter<ArticlesViewAdapter.ViewHolder>() {
    private var articles = mutableListOf<Article>()

    fun setList(list: List<Article>) {
        this.articles.clear()
        this.articles.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ArticlesSmallFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    class ViewHolder(
        private val binding: ArticlesSmallFragmentBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            val requestOptions =
                RequestOptions().transform(RoundedCorners(2.dpToPx(itemView.context)))
            binding.articleName.text = article.name
            Glide.with(itemView).load(article.img).placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                .apply(requestOptions).into(binding.articlePreviewImg)
            itemView.setOnClickListener { clickListener.onArticleClick(article) }
        }

        fun interface OnClickListener {
            fun onArticleClick(article: Article)
        }
    }
}
