package com.solosol.a32th_sopt_sopkathon_7_android

import android.content.Intent
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

class NewFragment : BaseViewBindingFragment<FragmentNewBinding>() {
    private val newRVAdapter =  NewRVAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            setRVData()
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): FragmentNewBinding {
        return FragmentNewBinding.inflate(layoutInflater)
    }

    private fun setRVAdapter(itemList: List<NewArticleResponse.Data?>?) {
        with(binding) {
            rvNew.adapter = newRVAdapter.apply {
                this.setItemClickListener(object : NewRVAdapter.OnItemClickListener {
                    override fun onItemClick(v: View, position: Int) {
                        val postId = itemList?.get(position)?.postId
                        val intent = Intent(requireContext(), CommentActivity::class.java).apply {
                            putExtra("postId", postId)
                        }
                        startActivity(intent)
                    }
                })
            }
            rvNew.layoutManager = LinearLayoutManager(requireContext())
            newRVAdapter.submitList(itemList)
        }
    }

    private suspend fun setRVData() {
        lifecycleScope.launch {
            val subwayName = arguments?.getString("subway_name") ?:return@launch
            val response = ApiFactory.soptService.getNewList(subwayName)
            if (response.isSuccessful) {
                val itemList: List<NewArticleResponse.Data?>? = response.body()?.data
                setRVAdapter(itemList)
            } else {
                Log.e("error", response.errorBody().toString())
            }
        }
    }

    companion object {
        fun getInstance(subwayName: String): NewFragment {
            val fragment = NewFragment()
            val bundle = Bundle()
            bundle.putString("subway_name", subwayName)
            fragment.arguments = bundle
            return fragment
        }
    }
}