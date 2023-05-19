package com.solosol.a32th_sopt_sopkathon_7_android.sample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingFragment
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.FragmentSampleBinding

class SampleFragment : BaseViewBindingFragment<FragmentSampleBinding>() {
    override fun setBinding(layoutInflater: LayoutInflater): FragmentSampleBinding {
        return FragmentSampleBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /** init 로직 구현 */
    }
}