package com.example.appmovil.ui.theme.ui.cart

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PurchasePrefs {

    private const val PREFS_NAME = "purchase_prefs"
    private const val KEY_PURCHASES = "purchase_history"

    private val gson = Gson()

    fun savePurchase(context: Context, purchase: Purchase) {
        val list = getPurchases(context).toMutableList()
        list.add(purchase)

        val json = gson.toJson(list)

        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_PURCHASES, json)
            .apply()
    }

    fun getPurchases(context: Context): List<Purchase> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_PURCHASES, null) ?: return emptyList()

        val type = object : TypeToken<List<Purchase>>() {}.type
        return gson.fromJson(json, type)
    }
}
