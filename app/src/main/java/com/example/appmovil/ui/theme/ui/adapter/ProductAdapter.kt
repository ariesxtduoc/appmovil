package com.example.appmovil.ui.theme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appmovil.databinding.ItemProductBinding
import com.example.appmovil.ui.theme.domain.model.Product



class ProductAdapter(
    private val onAddToCartClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList: List<Product> = emptyList()

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {


            binding.txtNombreProducto.text = product.name
            binding.txtPrecioProducto.text = "$${product.price}"

            binding.root.setOnClickListener {
                onAddToCartClick(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    override fun getItemCount(): Int = productList.size

    fun submitList(newList: List<Product>) {
        productList = newList
        notifyDataSetChanged()
    }
}
