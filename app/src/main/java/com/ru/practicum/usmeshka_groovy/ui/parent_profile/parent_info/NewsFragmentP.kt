package com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.practicum.usmeshka_groovy.databinding.NewsFragmentPBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.NewsSalesViewModel
import com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info.adapters.NewsViewAdapterP
import com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info.adapters.SalesViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragmentP : Fragment() {
    private var _binding: NewsFragmentPBinding? = null
    private val viewModel: NewsSalesViewModel by viewModel()

    private val binding get() = _binding!!
    private var newsViewAdapterP: NewsViewAdapterP? = null
    private var salesViewAdapter: SalesViewAdapter? = null
    private lateinit var recyclerViewSales: RecyclerView
    private lateinit var recyclerViewNews: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsFragmentPBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerViewSales = binding.recyclerSales
        recyclerViewNews = binding.recyclerNews
        newsViewAdapterP = NewsViewAdapterP {

        }
        salesViewAdapter = SalesViewAdapter {

        }
        binding.recyclerNews.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsViewAdapterP
        }
        binding.recyclerSales.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = salesViewAdapter
        }
        recyclerViewNews.adapter = newsViewAdapterP
        recyclerViewSales.adapter = salesViewAdapter
        viewModel.getNewsLiveData().observe(viewLifecycleOwner) { news ->
            newsViewAdapterP?.setList(news)
        }
        viewModel.getSalesLiveData().observe(viewLifecycleOwner) { sales ->
            salesViewAdapter?.setList(sales)
        }
        viewModel.getNews()
        viewModel.getSales()
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }

    companion object {
        fun newInstance() = NewsFragmentP().apply {}
    }
}
