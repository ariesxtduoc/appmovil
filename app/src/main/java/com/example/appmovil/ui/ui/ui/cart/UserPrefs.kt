package com.example.appmovil.data

import android.content.Context

object UserPrefs {

    private const val PREFS_NAME = "user_prefs"
    private const val KEY_NAME = "user_name"
    private const val KEY_EMAIL = "user_email"

    fun saveUser(context: Context, name: String, email: String) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email)
            .apply()
    }

    fun getUserName(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_NAME, "Usuario Desconocido") ?: "Usuario Desconocido"
    }

    fun getUserEmail(context: Context): String {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_EMAIL, "Sin correo") ?: "Sin correo"
    }
}
