package com.example.appmovil.ui.theme.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appmovil.ui.theme.domain.model.Product

/**
 * ViewModel para el HomeFragment.
 * Se encarga de la lógica de negocio y mantiene el estado de la lista de productos.
 */
class HomeViewModel : ViewModel() {

    // MutableLiveData es donde se pueden modificar los datos (solo accesible desde el ViewModel)
    private val _products = MutableLiveData<List<Product>>()

    // LiveData es la versión inmutable que el Fragmento observará para actualizar la UI
    val products: LiveData<List<Product>> = _products

    init {
        // Al inicializar el ViewModel, cargamos la lista de productos
        loadProducts()
    }

    /**
     * Simula la carga de productos (más tarde, aquí iría la llamada a la base de datos).
     */
    private fun loadProducts() {
        // Los mismos datos de prueba que usamos antes
        val sampleProducts = listOf(
            Product(
                id = "1",
                name = "Tomate Chonto Orgánico",
                description = "Sabor dulce y fresco. Ideal para ensaladas o salsas.",
                price = 3.50,
                unit = "Kg",
                imageUrl = "https://placehold.co/600x400/38761D/ffffff?text=TOMATE"
            ),
            Product(
                id = "2",
                name = "Lechuga Romana Fresca",
                description = "Hojas crujientes, de color verde intenso.",
                price = 1.20,
                unit = "Unidad",
                imageUrl = "https://placehold.co/600x400/4F7942/ffffff?text=LECHUGA"
            ),
            Product(
                id = "3",
                name = "Cebolla Cabezona",
                description = "Gran tamaño, perfecta para sofritos y caldos.",
                price = 1.50,
                unit = "Kg",
                imageUrl = "https://placehold.co/600x400/999999/ffffff?text=CEBOLLA"
            ),
        )
        // Publicamos los datos en el LiveData
        _products.value = sampleProducts
    }
}