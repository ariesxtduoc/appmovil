package com.example.appmovil.ui.ui.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.databinding.ItemPurchaseBinding
import java.text.SimpleDateFormat
import java.util.*

class PurchaseHistoryAdapter(private val purchases: List<Purchase>) :
    RecyclerView.Adapter<PurchaseHistoryAdapter.PurchaseViewHolder>() {

    inner class PurchaseViewHolder(val binding: ItemPurchaseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val binding = ItemPurchaseBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PurchaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = purchases[position]

        //  todos los nombres de productos separados por coma
        holder.binding.tvPurchaseName.text =
            purchase.items.joinToString(", ") { it.product.name }

        //  total
        holder.binding.tvPurchasePrice.text = "Total: $${purchase.total}"

        //  fecha de la compra
        val date = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            .format(Date(purchase.timestamp))
        holder.binding.tvPurchaseDate.text = date
    }

    override fun getItemCount(): Int = purchases.size
}
