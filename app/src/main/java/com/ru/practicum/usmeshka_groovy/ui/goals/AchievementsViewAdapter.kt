package com.ru.practicum.usmeshka_groovy.ui.goals

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.usmeshka_groovy.databinding.AchievementFragmentBinding
import com.practicum.usmeshka_groovy.databinding.NotifyCardsFragmentBinding
import com.ru.practicum.usmeshka_groovy.domain.models.Achievement
import com.ru.practicum.usmeshka_groovy.domain.models.Notification
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AchievementsViewAdapter() :
    RecyclerView.Adapter<AchievementsViewAdapter.ViewHolder>() {
    private var achievements = mutableListOf<Achievement>()

    fun setList(list: List<Achievement>) {

        this.achievements.clear()
        this.achievements.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            AchievementFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
        )
    }

    override fun getItemCount(): Int = achievements.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(achievements[position])
    }

    class ViewHolder(
        private val binding: AchievementFragmentBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(achievement: Achievement) {
            binding.achievementName.text = achievement.name
            binding.achievement.text = achievement.achievement
            binding.count.text = achievement.boopio.toString()
        }
    }
}

