package com.ru.practicum.usmeshka_groovy.ui.child_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.usmeshka_groovy.databinding.NotifyCardsFragmentBinding
import com.ru.practicum.usmeshka_groovy.domain.models.Notification
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class NotificationsViewAdapter(private val clickListener: ViewHolder.OnClickListener) :
    RecyclerView.Adapter<NotificationsViewAdapter.ViewHolder>() {
    private var notifications = mutableListOf<Notification>()

    fun setList(list: List<Notification>) {
        this.notifications.clear()
        this.notifications.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            NotifyCardsFragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = notifications.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(notifications[position])
    }

    class ViewHolder(
        private val binding: NotifyCardsFragmentBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(notification: Notification) {
            binding.notificationTitle.text = notification.message
            binding.notificationRepeating.text = notification.repeating.strValue
            binding.notificationDate.text = convertTimestampToString(notification.date)
            itemView.setOnClickListener { clickListener.onNotificationClick(notification) }
        }

        fun interface OnClickListener {
            fun onNotificationClick(notification: Notification)
        }

        private fun convertTimestampToString(timestamp: Long): String {
            val date = Date(timestamp)
            val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
            return formatter.format(date)
        }
    }
}
