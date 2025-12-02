package com.example.appmovil.ui.ui.ui.cart

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object PurchasePrefs {

    private const val PREFS_NAME = "purchase_prefs"
    private const val KEY_PURCHASES = "purchase_history"

    private val gson = Gson()

    // Guardar una compra
    fun savePurchase(context: Context, purchase: Purchase) {
        // Obtener historial previo
        val list = getPurchases(context).toMutableList()

        // Agregar nueva compra
        list.add(purchase)

        // Convertir a JSON
        val json = gson.toJson(list)

        // Guardar en SharedPreferences
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(KEY_PURCHASES, json)
            .apply()
    }

    // Obtener todas las compras
    fun getPurchases(context: Context): List<Purchase> {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_PURCHASES, null) ?: return emptyList()

        val type = object : TypeToken<List<Purchase>>() {}.type
        return try {
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList() // Evita crash si JSON está corrupto
        }
    }

    // Obtener compras filtradas por usuario
    fun getPurchasesForUser(context: Context, userId: String): List<Purchase> {
        return getPurchases(context).filter { it.userId == userId }
    }

    // Limpiar historial completo (opcional)
    fun clearAllPurchases(context: Context) {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .remove(KEY_PURCHASES)
            .apply()
    }
}
