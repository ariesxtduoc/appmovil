package com.example.appmovil.ui.theme.domain.model
import com.example.appmovil.ui.theme.domain.model.Product

/**
 * Objeto que contiene la lista completa de productos del catálogo HuertoHogar.
 */
object ProductsData {

    val productList: List<Product> = listOf(
        Product(
            id = "FR001",
            name = "Manzanas Fuji",
            description = "Manzanas Fuji crujientes y dulces, cultivadas en el Valle del Maule.",
            price = 1200.0,
            unit = "Kg",
            imageUrl = "https://picsum.photos/id/237/200/200"
        ),
        Product(
            id = "FR002",
            name = "Naranjas Valencia",
            description = "Jugosas y ricas en vitamina C, ideales para zumos frescos.",
            price = 1000.0,
            unit = "Kg",
            imageUrl = "https://placehold.co/600x400/FFA500/ffffff?text=Naranjas+Valencia"
        ),
        Product(
            id = "FR003",
            name = "Plátanos Cavendish",
            description = "Plátanos maduros y dulces, perfectos para snacks saludables.",
            price = 800.0,
            unit = "Kg",
            imageUrl = "https://placehold.co/600x400/F7E967/000000?text=Platanos+Cavendish"
        ),
        Product(
            id = "VR001",
            name = "Zanahorias Orgánicas",
            description = "Zanahorias crujientes sin pesticidas, ricas en vitamina A.",
            price = 900.0,
            unit = "Kg",
            imageUrl = "https://placehold.co/600x400/FF9F1C/ffffff?text=Zanahorias+Organicas"
        ),
        Product(
            id = "VR002",
            name = "Espinacas Frescas",
            description = "Espinacas nutritivas ideales para ensaladas y batidos verdes.",
            price = 700.0,
            unit = "Bolsa 500g",
            imageUrl = "https://placehold.co/600x400/4CAF50/ffffff?text=Espinacas+Frescas"
        ),
        Product(
            id = "VR003",
            name = "Pimientos Tricolores",
            description = "Pimientos rojos, amarillos y verdes, ricos en antioxidantes.",
            price = 1500.0,
            unit = "Kg",
            imageUrl = "https://placehold.co/600x400/E74C3C/ffffff?text=Pimientos+Tricolores"
        ),
        Product(
            id = "PO001",
            name = "Miel Orgánica",
            description = "Miel pura producida por apicultores locales.",
            price = 5000.0,
            unit = "Frasco 500g",
            imageUrl = "https://placehold.co/600x400/D4A373/ffffff?text=Miel+Organica"
        )
    )
}
