package com.example.appmovil.ui.theme.ui.cart

import com.example.appmovil.ui.theme.ui.cart.Purchase
import com.example.appmovil.ui.theme.ui.cart.CartItem

object PurchaseRepository {

    private val purchaseHistory = mutableListOf<Purchase>()

    fun addPurchase(purchase: Purchase) {
        purchaseHistory.add(purchase)
    }

    fun getPurchaseHistory(): List<Purchase> {
        return purchaseHistory
    }
}
