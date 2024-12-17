package com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.usmeshka_groovy.databinding.ArticlesFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ArticlesViewModel
import com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.adapters.ArticlesViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesFragment : Fragment() {
    private var _binding: ArticlesFragmentBinding? = null
    private val viewModel: ArticlesViewModel by viewModel()

    private val binding get() = _binding!!
    private var articlesViewAdapter: ArticlesViewAdapter? = null
    private lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ArticlesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getArticles()
        recyclerView = binding.recyclerArticles
        articlesViewAdapter = ArticlesViewAdapter {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(it.articleUrl)
            }
            startActivity(intent)
        }

        binding.recyclerArticles.apply {
            layoutManager =  GridLayoutManager(requireContext(), 2)
            adapter = articlesViewAdapter
        }
        recyclerView.adapter = articlesViewAdapter
        viewModel.getAarticlesLiveData().observe(viewLifecycleOwner) { articles ->
            articlesViewAdapter?.setList(articles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    companion object {
        fun newInstance() = ArticlesFragment().apply {}
    }
}