package com.solosol.a32th_sopt_sopkathon_7_android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseViewBindingFragment<B: ViewBinding>: Fragment {

    lateinit var binding: B
    val bindingInitialized: Boolean
        get() = this::binding.isInitialized && isAdded

    abstract fun setBinding(layoutInflater: LayoutInflater): B


    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return setBinding(layoutInflater).also { binding = it }.root
    }
}