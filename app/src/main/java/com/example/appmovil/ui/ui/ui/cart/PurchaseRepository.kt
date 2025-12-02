package com.example.appmovil.ui.ui.ui.cart

object PurchaseRepository {

    private val purchaseHistory = mutableListOf<Purchase>()

    fun addPurchase(purchase: Purchase) {
        purchaseHistory.add(purchase)
    }

    fun getPurchaseHistory(): List<Purchase> {
        return purchaseHistory
    }
}
