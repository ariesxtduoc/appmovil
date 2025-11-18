package com.example.appmovil.ui.theme.domain.model

/**
 * Clase de datos que representa un producto del catálogo HuertoHogar.
 * Este modelo define la estructura de la información que se mostrará en cada tarjeta del catálogo.
 * * @property id Identificador único del producto.
 * @property name Nombre del producto (ej: "Tomate Chonto").
 * @property description Descripción breve del producto.
 * @property price Precio por unidad.
 * @property unit Unidad de medida o venta (ej: "Kg", "Unidad", "Atado").
 * @property imageUrl URL de la imagen del producto (usada por la librería Coil).
 */
data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val unit: String,
    val imageUrl: String
) {
    // Constructor sin argumentos requerido para la deserialización automática
    // de sistemas de persistencia como Firebase Firestore.
    constructor() : this("", "", "", 0.0, "", "")
}