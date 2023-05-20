package com.solosol.a32th_sopt_sopkathon_7_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.TrendArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemPostBinding
import com.solosol.a32th_sopt_sopkathon_7_android.util.DiffCallback
import com.solosol.a32th_sopt_sopkathon_7_android.util.TimeUtil

class TrendRVAdapter(private val onclick:(Int) -> Unit) : ListAdapter<TrendArticleResponse.Data, TrendRVAdapter.TrendViewHolder>(TrendDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TrendViewHolder {
        return TrendViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bind(currentList[position]!!)
    }

    inner class TrendViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TrendArticleResponse.Data) {
            with(binding){
                tvTitle.text = item.postTitle
                tvComment.text = item.postCommentCnt.toString()
                tvContent.text= item.postContent
                tvThumb.text = item.postLikeCnt.toString()
                tvTime.text = TimeUtil.calculateTimeDifference(item.postCreatedAt ?:"")
            }
            itemView.setOnClickListener {
                onclick(item.postId ?:0)
            }
        }
    }

    companion object {
        private val TrendDiffCallback =
            DiffCallback<TrendArticleResponse.Data>(id = { old, new -> old.postId == new.postId})
    }
}