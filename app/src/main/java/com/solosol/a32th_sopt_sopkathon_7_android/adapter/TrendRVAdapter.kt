package com.solosol.a32th_sopt_sopkathon_7_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.TrendArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemPostBinding
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemSampleBinding
import com.solosol.a32th_sopt_sopkathon_7_android.sample.SampleData
import com.solosol.a32th_sopt_sopkathon_7_android.util.DiffCallback

class TrendRVAdapter(_itemList:List<TrendArticleResponse.Data?>?) : ListAdapter<TrendArticleResponse.Data, TrendRVAdapter.TrendViewHolder>(TrendDiffCallback) {
    private val itemList = _itemList!!
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TrendViewHolder {
        return TrendViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bind(itemList[position]!!)
    }

    class TrendViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendArticleResponse.Data) {
            with(binding){
                tvTitle.text = item.postTitle
                tvComment.text = item.postCommentCnt.toString()
                tvContent.text= item.postContent
                tvThumb.text = item.postLikeCnt.toString()
            }
        }
    }

    companion object {
        private val TrendDiffCallback =
            DiffCallback<TrendArticleResponse.Data>(id = { old, new -> old.postId == new.postId})
    }
}