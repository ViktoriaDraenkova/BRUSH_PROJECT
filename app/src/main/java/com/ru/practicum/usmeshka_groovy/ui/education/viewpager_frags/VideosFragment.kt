package com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.practicum.usmeshka_groovy.databinding.VideosFragmentBinding
import com.ru.practicum.usmeshka_groovy.presentation.viewmodel.VideoViewModel
import com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.adapters.ArticlesViewAdapter
import com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.adapters.VideosViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideosFragment :  Fragment() {
    private val viewModel: VideoViewModel by viewModel()
    private var _binding: VideosFragmentBinding? = null
    private val binding get() = _binding!!
    private var videosViewAdapter: VideosViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = VideosFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        videosViewAdapter = VideosViewAdapter {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(it.videoUrl)
            }
            startActivity(intent)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = videosViewAdapter
        }

        viewModel.getVideosLiveData().observe(viewLifecycleOwner) {
            Log.d("AAA", it.toString())
            videosViewAdapter!!.setList(it)
        }
        viewModel.getVideos()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = VideosFragment().apply {}
    }
}