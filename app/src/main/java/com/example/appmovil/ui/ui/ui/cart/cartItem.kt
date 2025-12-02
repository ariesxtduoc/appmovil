package com.example.appmovil.ui.ui.ui.cart

import com.example.appmovil.ui.ui.domain.model.Product
import java.io.Serializable

data class CartItem(
    val product: Product,
    var quantity: Int = 1
) : Serializable
