package com.solosol.a32th_sopt_sopkathon_7_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingFragment
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.FragmentTrendBinding

class TrendFragment : BaseViewBindingFragment<FragmentTrendBinding>(){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun setBinding(layoutInflater: LayoutInflater): FragmentTrendBinding {
        return FragmentTrendBinding.inflate(layoutInflater)
    }
}