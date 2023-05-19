package com.solosol.a32th_sopt_sopkathon_7_android.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object SharedPreferences {
    private lateinit var preferences: SharedPreferences

    fun init(context: Context) {
        preferences = context.getSharedPreferences("com.solosol.a32th_sopt_sopkathon_7_android", Context.MODE_PRIVATE)
    }

    fun setString(key: String, value: String?) {
        preferences.edit {
            putString(key, value)
        }
    }

    fun getString(key: String): String? {
        return preferences.getString(key, "")
    }

    fun setBoolean(key: String, boolean: Boolean) {
        preferences.edit {
            putBoolean(key, boolean)
        }
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun clear() {
        preferences.edit {
            clear()
        }
    }
}