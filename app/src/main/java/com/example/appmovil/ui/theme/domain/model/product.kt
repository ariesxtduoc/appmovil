package com.example.appmovil.ui.theme.domain.model

import java.io.Serializable

data class Product(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val unit: String = "",
    val imageUrl: String = ""
) : Serializable
