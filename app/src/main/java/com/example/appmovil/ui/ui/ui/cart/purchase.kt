package com.example.appmovil.ui.ui.ui.cart

data class Purchase(
    val userId: String,
    val items: List<CartItem>,
    val total: Double,
    val timestamp: Long = System.currentTimeMillis()
)
