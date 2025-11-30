package com.example.appmovil.ui.theme.ui.cart


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.databinding.ItemCartBinding

class CartAdapter(
    private val onIncrease: (CartItem) -> Unit,
    private val onDecrease: (CartItem) -> Unit,
    private val onDelete: (CartItem) -> Unit
) : ListAdapter<CartItem, CartAdapter.CartViewHolder>(DiffCallback()) {

    class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = getItem(position)
        val b = holder.binding

        b.tvProductName.text = item.product.name
        b.tvProductPrice.text = "$${item.product.price}"
        b.tvQuantity.text = item.quantity.toString()

        b.btnPlus.setOnClickListener { onIncrease(item) }
        b.btnMinus.setOnClickListener { onDecrease(item) }
        b.btnDelete.setOnClickListener { onDelete(item) }
    }

    class DiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(old: CartItem, new: CartItem) =
            old.product.id == new.product.id

        override fun areContentsTheSame(old: CartItem, new: CartItem) =
            old == new
    }
}
