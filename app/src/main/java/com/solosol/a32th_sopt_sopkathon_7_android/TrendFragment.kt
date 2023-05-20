package com.solosol.a32th_sopt_sopkathon_7_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.solosol.a32th_sopt_sopkathon_7_android.adapter.TrendRVAdapter
import com.solosol.a32th_sopt_sopkathon_7_android.api.ApiFactory
import com.solosol.a32th_sopt_sopkathon_7_android.api.ApiFactory.soptService
import com.solosol.a32th_sopt_sopkathon_7_android.api.SoptService
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.NewArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.TrendArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.base.BaseViewBindingFragment
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.FragmentTrendBinding
import kotlinx.coroutines.launch

class TrendFragment : BaseViewBindingFragment<FragmentTrendBinding>() {

    private val trendRVAdapter = TrendRVAdapter {
        startActivity(Intent(requireContext(), CommentActivity::class.java).apply {
            putExtra("postId", it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            setRVData()
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): FragmentTrendBinding {
        return FragmentTrendBinding.inflate(layoutInflater)
    }

    private fun setRVAdapter(itemList: List<TrendArticleResponse.Data?>?) {
        with(binding) {
            rvTrend.adapter = trendRVAdapter
            rvTrend.layoutManager = LinearLayoutManager(requireContext())
        }
        trendRVAdapter.submitList(itemList)
    }

    private suspend fun setRVData() {
        lifecycleScope.launch {
            val subwayName = arguments?.getString("subway_name") ?: return@launch
            val response = soptService.getTrendList(subwayName)
            if (response.isSuccessful) {
                val itemList: List<TrendArticleResponse.Data?>? = response.body()?.data
                setRVAdapter(itemList)
            } else {
                Log.e("error", response.errorBody().toString())
            }
        }
    }

    companion object {
        fun getInstance(subwayName: String): TrendFragment {
            val fragment = TrendFragment()
            val bundle = Bundle()
            bundle.putString("subway_name", subwayName)
            fragment.arguments = bundle
            return fragment
        }
    }
}