package com.solosol.a32th_sopt_sopkathon_7_android



import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater

import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingActivity
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ActivityMainBinding



class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}