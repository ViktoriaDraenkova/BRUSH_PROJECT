package com.ru.practicum.usmeshka_groovy.ui.parent_boughts_fragment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ru.practicum.usmeshka_groovy.domain.models.Purchase
import com.practicum.usmeshka_groovy.R
import com.ru.practicum.usmeshka_groovy.util.PurchaseClickListener

class PurchasesViewAdapter(
    private val clickListener: PurchaseClickListener,
) : RecyclerView.Adapter<PurchaseViewHolder>() {

    private var purchases = emptyList<Purchase>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.purchases_item_view, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        holder.bind(purchases[position])
        holder.itemView.setOnClickListener { clickListener.onPurchaseClick(purchases[position]) }
    }

    override fun getItemCount() = purchases.size

    fun setPurchases(purchases: List<Purchase>) {
        this.purchases = purchases
        notifyDataSetChanged()
    }
}

class PurchaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val dateTime: TextView

    init {
        dateTime = itemView.findViewById(R.id.date_time)
    }

    @SuppressLint("SetTextI18n")
    fun bind(model: Purchase) {
        dateTime.text = model.datetime
    }
}

