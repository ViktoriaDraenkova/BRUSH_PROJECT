package com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.practicum.usmeshka_groovy.databinding.ArticlesFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.ArticlesViewModel
import com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.ArticlesFragment
import com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info.adapters.ArticlesViewAdapterP
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArticlesFragmentP : Fragment() {
    private var _binding: ArticlesFragmentBinding? = null
    private val viewModel: ArticlesViewModel by viewModel()

    private val binding get() = _binding!!
    private var articlesViewAdapter: ArticlesViewAdapterP? = null
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
        articlesViewAdapter = ArticlesViewAdapterP {
            findNavController().navigate(
                InfoFragmentDirections.actionInfoFragmentToArticleDetailFragment(
                    Gson().toJson(it)
                )
            )
        }

        binding.recyclerArticles.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
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