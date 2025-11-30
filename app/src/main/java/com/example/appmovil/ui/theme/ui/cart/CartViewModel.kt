package com.example.appmovil.ui.theme.ui.cart



import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<List<CartItem>>()
    val cartItems: LiveData<List<CartItem>> get() = _cartItems

    fun loadCart(context: Context) {
        _cartItems.value = CartPrefs.getCart(context)
    }

    fun updateQuantity(context: Context, productId: String, quantity: Int) {
        CartPrefs.updateQuantity(context, productId, quantity)
        loadCart(context)
    }

    fun removeItem(context: Context, productId: String) {
        CartPrefs.removeProduct(context, productId)
        loadCart(context)
    }

    fun getTotal(): Double {
        return _cartItems.value?.sumOf { it.product.price * it.quantity } ?: 0.0
    }
}
