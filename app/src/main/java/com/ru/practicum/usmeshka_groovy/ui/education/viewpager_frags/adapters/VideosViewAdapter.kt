package com.ru.practicum.usmeshka_groovy.ui.education.viewpager_frags.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.VideosSmallFragmentBinding
import com.ru.practicum.usmeshka_groovy.domain.models.Video
import com.ru.practicum.usmeshka_groovy.util.dpToPx

class VideosViewAdapter(private val clickListener: ViewHolder.OnClickListener) :
    RecyclerView.Adapter<VideosViewAdapter.ViewHolder>() {
    private var videos = mutableListOf<Video>()

    fun setList(list: List<Video>) {
        this.videos.clear()
        this.videos.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            VideosSmallFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(videos[position])
    }

    class ViewHolder(
        private val binding: VideosSmallFragmentBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(video: Video) {
            val requestOptions =
                RequestOptions().transform(RoundedCorners(24.dpToPx(itemView.context)))
            binding.videoName.text = video.name
            binding.channel.text = video.channelName
            Glide.with(itemView).load(video.preview).placeholder(R.drawable.placeholder).error(R.drawable.placeholder)
                .apply(requestOptions).into(binding.video)

            itemView.setOnClickListener { clickListener.onVideoClick(video) }
        }

        fun interface OnClickListener {
            fun onVideoClick(video: Video)
        }
    }
}