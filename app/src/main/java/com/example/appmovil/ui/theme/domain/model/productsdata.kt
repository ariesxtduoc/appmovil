package com.example.appmovil.ui.theme.domain.model

/**
 * Objeto que contiene la lista completa de productos del catálogo HuertoHogar.
 */
object ProductsData {

    val productList: List<Product> = listOf(

        // --- FRUTAS ---
        Product(
            id = "FR001",
            name = "Manzanas Fuji",
            description = "Manzanas crujientes y dulces, cultivadas en el Valle del Maule.",
            price = 1200.0,
            unit = "Kg",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg"
        ),
        Product(
            id = "FR002",
            name = "Naranjas Valencia",
            description = "Jugosas y ricas en vitamina C, ideales para zumos frescos.",
            price = 1000.0,
            unit = "Kg",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"
        ),
        Product(
            id = "FR003",
            name = "Plátanos Cavendish",
            description = "Plátanos maduros y dulces, perfectos para snacks saludables.",
            price = 800.0,
            unit = "Kg",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg"
        ),

        // --- VERDURAS ---
        Product(
            id = "VR001",
            name = "Zanahorias Orgánicas",
            description = "Zanahorias crujientes sin pesticidas, ricas en vitamina A.",
            price = 900.0,
            unit = "Kg",
            imageUrl = "https://images.pexels.com/photos/65174/pexels-photo-65174.jpeg"
        ),
        Product(
            id = "VR002",
            name = "Espinacas Frescas",
            description = "Espinacas nutritivas ideales para ensaladas y batidos verdes.",
            price = 700.0,
            unit = "Bolsa 500g",
            imageUrl = "https://www.conasi.eu/blog/wp-content/uploads/2023/07/recetas-con-espinacas-1.jpg"
        ),
        Product(
            id = "VR003",
            name = "Pimientos Tricolores",
            description = "Pimientos rojos, amarillos y verdes, ricos en antioxidantes.",
            price = 1500.0,
            unit = "Kg",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNiOrmATgtFB5YMlVsXm5VTqalOZbBfUd3Gg&s"
        ),

        // --- ORGÁNICOS ---
        Product(
            id = "PO001",
            name = "Miel Orgánica",
            description = "Miel pura producida por apicultores locales.",
            price = 5000.0,
            unit = "Frasco 500g",
            imageUrl = "https://www.gob.mx/cms/uploads/article/main_image/26545/miel.jpg"
        ),
        Product(
            id = "PO003",
            name = "Quinua Orgánica",
            description = "Fuente de proteínas vegetales y fibra.",
            price = 3200.0,
            unit = "Bolsa 1kg",
            imageUrl = "https://agraria.pe/imgs/a/lx/peru-exporto-por-primera-vez-quinua-organica-a-brasil-16694.jpg"
        ),

        // --- LÁCTEOS ---
        Product(
            id = "PL001",
            name = "Leche Entera",
            description = "Leche fresca proveniente de granjas locales.",
            price = 1200.0,
            unit = "1 Litro",
            imageUrl = "https://images.pexels.com/photos/236010/pexels-photo-236010.jpeg"
        )
    )
}
