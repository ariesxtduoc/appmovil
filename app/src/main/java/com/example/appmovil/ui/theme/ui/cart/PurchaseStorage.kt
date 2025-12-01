package com.example.appmovil.storage

import android.content.Context
import com.example.appmovil.ui.theme.ui.cart.CartItem
import com.example.appmovil.ui.theme.ui.cart.Purchase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PurchaseStorage {

    private const val PREF_NAME = "purchase_history"
    private const val KEY_HISTORY = "history"

    fun savePurchase(context: Context, purchase: Purchase) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val gson = Gson()

        val existingJson = prefs.getString(KEY_HISTORY, "[]")
        val type = object : TypeToken<MutableList<Purchase>>() {}.type

        val purchaseList: MutableList<Purchase> = gson.fromJson(existingJson, type)
        purchaseList.add(purchase)

        prefs.edit().putString(KEY_HISTORY, gson.toJson(purchaseList)).apply()
    }

    fun getPurchases(context: Context): List<Purchase> {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_HISTORY, "[]")
        val type = object : TypeToken<List<Purchase>>() {}.type

        return Gson().fromJson(json, type)
    }
}
