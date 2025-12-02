package com.example.appmovil.ui.ui.domain.model

/**
 * Objeto que contiene la lista completa de productos del catálogo HuertoHogar.
 */
object ProductsData {

    val productList: List<Product> = listOf(

        // --- FRUTAS ---
        Product(
            id = "FR001",
            name = "Manzanas Fuji",
            description = "Manzanas Fuji crujientes y dulces, cultivadas en el Valle del Maule." +
                    "Perfectas para meriendas saludables o como ingrediente en postres. Estas manzanas" +
                    "son conocidas por su textura firme y su sabor equilibrado entre dulce y ácido",
            price = 1200.0,
            unit = "Kg",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg"
        ),
        Product(
            id = "FR002",
            name = "Naranjas Valencia",
            description = "Jugosas y ricas en vitamina C, estas naranjas Valencia son ideales para" +
                    "zumos frescos y refrescantes. Cultivadas en condiciones climáticas óptimas que" +
                    "aseguran su dulzura y jugosidad",
            price = 1000.0,
            unit = "Kg",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg"
        ),
        Product(
            id = "FR003",
            name = "Plátanos Cavendish",
            description = "Plátanos maduros y dulces, perfectos para el desayuno o como snack" +
                    "energético. Estos plátanos son ricos en potasio y vitaminas, ideales para mantener una" +
                    "dieta equilibrada.",
            price = 800.0,
            unit = "Kg",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/8/8a/Banana-Single.jpg"
        ),

        // --- VERDURAS ---
        Product(
            id = "VR001",
            name = "Zanahorias Orgánicas",
            description = "Zanahorias crujientes cultivadas sin pesticidas en la Región de O'Higgins." +
                    "Excelente fuente de vitamina A y fibra, ideales para ensaladas, jugos o como snack" +
                    "saludable",
            price = 900.0,
            unit = "Kg",
            imageUrl = "https://images.pexels.com/photos/65174/pexels-photo-65174.jpeg"
        ),
        Product(
            id = "VR002",
            name = "Espinacas Frescas",
            description = "Espinacas frescas y nutritivas, perfectas para ensaladas y batidos verdes." +
                    "Estas espinacas son cultivadas bajo prácticas orgánicas que garantizan su calidad y valor" +
                    "nutricional.",
            price = 700.0,
            unit = "Bolsa 500g",
            imageUrl = "https://www.conasi.eu/blog/wp-content/uploads/2023/07/recetas-con-espinacas-1.jpg"
        ),
        Product(
            id = "VR003",
            name = "Pimientos Tricolores",
            description = "Pimientos rojos, amarillos y verdes, ideales para salteados y platos" +
                    "coloridos. Ricos en antioxidantes y vitaminas, estos pimientos añaden un toque vibrante" +
                    "y saludable a cualquier receta.",
            price = 1500.0,
            unit = "Kg",
            imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNiOrmATgtFB5YMlVsXm5VTqalOZbBfUd3Gg&s"
        ),

        // --- ORGÁNICOS ---
        Product(
            id = "PO001",
            name = "Miel Orgánica",
            description = "Miel pura y orgánica producida por apicultores locales. Rica en\n" +
                    "antioxidantes y con un sabor inigualable, perfecta para endulzar de manera natural tus\n" +
                    "comidas y bebidas.",
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
