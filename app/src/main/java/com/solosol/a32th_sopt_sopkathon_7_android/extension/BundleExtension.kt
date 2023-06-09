package com.solosol.a32th_sopt_sopkathon_7_android.extension

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.TIRAMISU
import android.os.Bundle
import android.os.Parcelable

fun <T : Parcelable?> Bundle.getExtParcelable(key: String, clazz: Class<T>): T? = when {
    SDK_INT >= TIRAMISU -> getParcelable(key, clazz)
    else -> getParcelable(key)
}

