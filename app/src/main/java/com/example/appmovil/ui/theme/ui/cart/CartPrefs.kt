package com.example.appmovil.ui.theme.ui.cart



import android.content.Context
import com.example.appmovil.ui.theme.domain.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartPrefs {

    private const val PREF_NAME = "cart_pref"
    private const val KEY_CART = "cart_items"

    private val gson = Gson()

    // Obtener carrito completo
    fun getCart(context: Context): MutableList<CartItem> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_CART, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<CartItem>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }

    // Guardar carrito
    private fun saveCart(context: Context, items: List<CartItem>) {
        val json = gson.toJson(items)
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_CART, json)
            .apply()
    }

    // Agregar producto (si ya existe aumenta cantidad)
    fun addProduct(context: Context, product: Product, qty: Int = 1) {
        val cart = getCart(context)
        val existingIndex = cart.indexOfFirst { it.product.id == product.id }

        if (existingIndex >= 0) {
            cart[existingIndex].quantity += qty
        } else {
            cart.add(CartItem(product, qty))
        }

        saveCart(context, cart)
    }

    // Remover producto completo
    fun removeProduct(context: Context, productId: String) {
        val cart = getCart(context).filter { it.product.id != productId }
        saveCart(context, cart)
    }

    // Cambiar cantidad
    fun updateQuantity(context: Context, productId: String, qty: Int) {
        val cart = getCart(context)
        val index = cart.indexOfFirst { it.product.id == productId }

        if (index >= 0) {
            if (qty <= 0) {
                cart.removeAt(index)
            } else {
                cart[index].quantity = qty
            }
            saveCart(context, cart)
        }
    }

    // Vaciar carrito
    fun clearCart(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(KEY_CART)
            .apply()
    }

    // Obtener total del carrito
    fun getTotal(context: Context): Double {
        return getCart(context).sumOf { it.product.price * it.quantity }
    }
}
