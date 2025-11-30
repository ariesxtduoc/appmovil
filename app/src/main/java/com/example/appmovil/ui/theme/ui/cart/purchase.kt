package com.example.appmovil.ui.theme.ui.cart

data class Purchase(
    val items: List<CartItem>,
    val total: Double,
    val timestamp: Long = System.currentTimeMillis()
)
