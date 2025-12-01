package com.example.appmovil.ui.theme.ui.cart

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> get() = _cartItems

    // Cargar carrito filtrado por usuario
    fun loadCart(context: Context, userId: String) {
        _cartItems.value = CartPrefs.getCart(context, userId)
    }

    // Actualizar cantidad de un producto para un usuario específico
    fun updateQuantity(context: Context, productId: String, quantity: Int, userId: String) {
        CartPrefs.updateQuantity(context, productId, quantity, userId)
        loadCart(context, userId)
    }

    // Eliminar un producto del carrito de un usuario específico
    fun removeItem(context: Context, productId: String, userId: String) {
        CartPrefs.removeProduct(context, productId, userId)
        loadCart(context, userId)
    }

    // Calcular total del carrito de un usuario específico
    fun getTotal(userId: String): Double {
        return _cartItems.value?.sumOf { it.product.price * it.quantity } ?: 0.0
    }

    // Registrar compra y limpiar carrito para un usuario específico
    fun confirmPurchase(context: Context, userId: String) {
        val items = _cartItems.value ?: emptyList()
        val total = getTotal(userId)

        val purchase = Purchase(
            userId = userId,
            items = items,
            total = total
        )

        // Guardar compra en historial
        PurchasePrefs.savePurchase(context, purchase)

        // Limpiar carrito del usuario
        CartPrefs.clearCart(context, userId)

        // Refrescar LiveData
        loadCart(context, userId)
    }
}
