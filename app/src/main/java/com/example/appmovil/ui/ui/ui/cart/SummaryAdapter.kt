package com.example.appmovil.ui.ui.ui.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.databinding.ItemSummaryBinding

class SummaryAdapter(
    private val items: List<CartItem>
) : RecyclerView.Adapter<SummaryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemSummaryBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSummaryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]


        holder.binding.txtProducto.text = item.product.name
        holder.binding.txtCantidad.text = "x${item.quantity}"
        holder.binding.txtSubtotal.text = "$${item.product.price * item.quantity}"
    }

    override fun getItemCount(): Int = items.size
}
