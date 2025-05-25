package com.ru.practicum.usmeshka_groovy.ui.parent_profile.parent_info.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.practicum.usmeshka_groovy.R
import com.practicum.usmeshka_groovy.databinding.SaleSmallBinding
import com.ru.practicum.usmeshka_groovy.domain.models.Sale
import com.ru.practicum.usmeshka_groovy.util.dpToPx

class SalesViewAdapter(private val clickListener: ViewHolder.OnClickListener) :
    RecyclerView.Adapter<SalesViewAdapter.ViewHolder>() {
    private var sales = mutableListOf<Sale>()

    fun setList(list: List<Sale>) {
        this.sales.clear()
        this.sales.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SaleSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = sales.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sales[position])
    }

    class ViewHolder(
        private val binding: SaleSmallBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(sale: Sale) {
            Log.d("Rendering sale", sale.toString())
            val requestOptions =
                RequestOptions().transform(RoundedCorners(2.dpToPx(itemView.context)))
            Glide.with(itemView).load(sale.salePreview).placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .apply(requestOptions).into(binding.saleImg)
            itemView.setOnClickListener { clickListener.onSaleClick(sale) }
        }

        fun interface OnClickListener {
            fun onSaleClick(sale: Sale)
        }
    }
}