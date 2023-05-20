package com.solosol.a32th_sopt_sopkathon_7_android

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.solosol.a32th_sopt_sopkathon_7_android.adapter.NewRVAdapter
import com.solosol.a32th_sopt_sopkathon_7_android.api.ApiFactory
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.NewArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.TrendArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingFragment
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.FragmentNewBinding
import kotlinx.coroutines.launch

class NewFragment:BaseViewBindingFragment<FragmentNewBinding>() {
    var subwayName = ""
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            setRVData()
        }
    }
    override fun setBinding(layoutInflater: LayoutInflater): FragmentNewBinding {
        return FragmentNewBinding.inflate(layoutInflater)
    }

    private fun setRVAdapter(itemList:List<NewArticleResponse.Data?>?){
        with(binding){
            rvNew.adapter = NewRVAdapter(itemList)
            rvNew.layoutManager= LinearLayoutManager(requireContext())
        }
    }

    private suspend fun setRVData() {
        lifecycleScope.launch {
            val response = ApiFactory.soptService.getNewList(subwayName)
            if (response.isSuccessful) {
                val itemList:List<NewArticleResponse.Data?>? = response.body()?.data
                setRVAdapter(itemList)
            } else {
                Log.e("error",response.errorBody().toString())
            }
        }
    }
}