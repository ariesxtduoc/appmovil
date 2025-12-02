package com.example.appmovil.ui.ui.ui.cart

import android.content.Context
import com.example.appmovil.ui.ui.domain.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CartPrefs {

    private const val PREF_NAME = "cart_pref"
    private const val KEY_CART = "cart_items"

    private val gson = Gson()

    // Construye la clave por usuario
    private fun getKey(userId: String) = "${KEY_CART}_$userId"

    // Obtener carrito del usuario
    fun getCart(context: Context, userId: String): MutableList<CartItem> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(getKey(userId), null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<CartItem>>() {}.type
        return gson.fromJson(json, type) ?: mutableListOf()
    }

    // Guardar carrito del usuario
    private fun saveCart(context: Context, items: List<CartItem>, userId: String) {
        val json = gson.toJson(items)
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(getKey(userId), json)
            .apply()
    }

    // Agregar producto al carrito del usuario
    fun addProduct(context: Context, product: Product, qty: Int = 1, userId: String) {
        val cart = getCart(context, userId)
        val existingIndex = cart.indexOfFirst { it.product.id == product.id }

        if (existingIndex >= 0) {
            cart[existingIndex].quantity += qty
        } else {
            cart.add(CartItem(product, qty))
        }

        saveCart(context, cart, userId)
    }

    // Remover producto del carrito del usuario
    fun removeProduct(context: Context, productId: String, userId: String) {
        val cart = getCart(context, userId).filter { it.product.id != productId }
        saveCart(context, cart, userId)
    }

    // Cambiar cantidad de un producto del carrito del usuario
    fun updateQuantity(context: Context, productId: String, qty: Int, userId: String) {
        val cart = getCart(context, userId)
        val index = cart.indexOfFirst { it.product.id == productId }

        if (index >= 0) {
            if (qty <= 0) {
                cart.removeAt(index)
            } else {
                cart[index].quantity = qty
            }
            saveCart(context, cart, userId)
        }
    }

    // Vaciar carrito del usuario
    fun clearCart(context: Context, userId: String) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(getKey(userId))
            .apply()
    }

    // Obtener total del carrito del usuario
    fun getTotal(context: Context, userId: String): Double {
        return getCart(context, userId).sumOf { it.product.price * it.quantity }
    }
}
