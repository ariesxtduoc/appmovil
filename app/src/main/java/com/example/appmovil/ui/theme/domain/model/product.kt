package com.example.appmovil.ui.theme.domain.model

/**
 * Clase de datos que representa un producto del catálogo HuertoHogar.
 */
data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val unit: String = "",
    val imageUrl: String = ""
)
