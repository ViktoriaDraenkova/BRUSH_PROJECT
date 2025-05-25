package com.ru.practicum.usmeshka_groovy.ui.parent_boughts_fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ru.practicum.usmeshka_groovy.domain.models.Purchase
import com.practicum.usmeshka_groovy.R
import com.ru.practicum.usmeshka_groovy.ui.cart.CartProductsViewAdapter

class PurchaseSummaryDialog(private val purchase: Purchase) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_purchase_summary, container, false)

        val datetimeTextView: TextView = view.findViewById(R.id.datetimeTextView)
        val itemsRecyclerView: RecyclerView = view.findViewById(R.id.itemsRecyclerView)
        val closeButton: Button = view.findViewById(R.id.closeButton)

        datetimeTextView.text = "от: ${purchase.datetime}"

        itemsRecyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = CartProductsViewAdapter(
            { return@CartProductsViewAdapter true },
            { return@CartProductsViewAdapter true },
            {},
            true
        )
        itemsRecyclerView.adapter = adapter
        adapter.setProducts(purchase.items.map { it.product }, purchase.items.map {it.count.toLong()})

        closeButton.setOnClickListener {
            dismiss()
        }

        return view
    }
}