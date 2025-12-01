package com.example.appmovil.ui.theme.ui.profile

import android.content.Context
import com.google.gson.Gson

object UserPrefs {

    private const val PREFS_NAME = "user_prefs"
    private const val KEY_USER = "user_data"

    fun saveUser(context: Context, user: User) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = Gson().toJson(user)
        prefs.edit().putString(KEY_USER, json).apply()
    }

    fun getUser(context: Context): User {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY_USER, null)
        return if (json != null) Gson().fromJson(json, User::class.java) else User()
    }

    fun clearUser(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_USER).apply()
    }
}
