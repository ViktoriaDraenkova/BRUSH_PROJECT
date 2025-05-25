package com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.practicum.usmeshka_groovy.databinding.ArticleDetailFragmentBinding
import com.ru.practicum.usmeshka_groovy.domain.models.Article

class ArticleDetailFragment : Fragment() {

    private var _binding: ArticleDetailFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArticleDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ArticleDetailFragmentArgs by navArgs()
        val articleJson = args.article
        val article = Gson().fromJson(articleJson, Article::class.java)

        insertHtml(binding.articleText, article.bodyHtml)
    }

    fun insertHtml(textView: TextView, html: String) {
        textView.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(html)
        }
    }
}