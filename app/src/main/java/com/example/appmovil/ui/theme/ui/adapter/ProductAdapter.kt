package com.example.appmovil.ui.theme.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appmovil.databinding.ItemProductBinding
import com.example.appmovil.ui.theme.domain.model.Product

class ProductAdapter(
    private val onAddToCartClick: (Product) -> Unit,
    private val onItemClick: (Product) -> Unit   // 👈 NUEVO LISTENER
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var productList: List<Product> = emptyList()

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {

            // Imagen
            Glide.with(binding.root.context)
                .load(product.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .error(android.R.drawable.ic_menu_report_image)
                .centerCrop()
                .into(binding.imgProducto)

            // Nombre y precio
            binding.txtNombreProducto.text = product.name
            binding.txtPrecioProducto.text = "$${product.price}"

            // 👇 CLICK EN TODA LA TARJETA → DETALLE
            binding.root.setOnClickListener {
                onItemClick(product)
            }

            // 👇 SI TIENES BOTÓN "AGREGAR AL CARRITO" AQUÍ IRÍA
            // binding.btnAddToCart.setOnClickListener {
            //     onAddToCartClick(product)
            // }

            // 👉 Pero como NO existe aún, mantengo tu lógica actual:
            //    Agregar al carrito ocurre en HomeFragment, no aquí.
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
