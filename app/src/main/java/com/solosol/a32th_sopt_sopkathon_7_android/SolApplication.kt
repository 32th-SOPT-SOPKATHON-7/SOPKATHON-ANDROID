package com.solosol.a32th_sopt_sopkathon_7_android

import android.app.Application
import com.solosol.a32th_sopt_sopkathon_7_android.util.SharedPreferences

class SolApplication : Application() {

    companion object {
        lateinit var appContext: SolApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        initSharedPreferences()
    }

    private fun initSharedPreferences() {
        SharedPreferences.init(this)
    }
}