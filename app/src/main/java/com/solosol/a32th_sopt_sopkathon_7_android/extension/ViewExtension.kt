package com.solosol.a32th_sopt_sopkathon_7_android.extension

import android.view.View
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar


fun View.showSnack(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}
