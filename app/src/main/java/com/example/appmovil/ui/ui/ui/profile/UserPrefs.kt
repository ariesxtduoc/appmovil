package com.example.appmovil.ui.ui.ui.profile

import android.content.Context

object UserPrefs {
    private const val PREFS_NAME = "registered_user"

    fun saveUser(context: Context, user: User) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .putString("name", user.name)
            .putString("email", user.email)
            .putString("password", user.password)
            .putString("address", user.address)
            .putString("phone", user.phone)
            .apply()
    }

    fun getUser(context: Context): User {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return User(
            name = prefs.getString("name", "") ?: "",
            email = prefs.getString("email", "") ?: "",
            password = prefs.getString("password", "") ?: "",
            address = prefs.getString("address", "") ?: "",
            phone = prefs.getString("phone", "") ?: ""
        )
    }
}
