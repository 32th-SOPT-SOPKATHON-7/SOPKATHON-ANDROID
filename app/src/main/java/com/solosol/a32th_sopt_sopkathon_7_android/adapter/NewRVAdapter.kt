package com.solosol.a32th_sopt_sopkathon_7_android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.NewArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.TrendArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemPostBinding
import com.solosol.a32th_sopt_sopkathon_7_android.sample.SampleData
import com.solosol.a32th_sopt_sopkathon_7_android.util.DiffCallback

class NewRVAdapter(_itemList:List<NewArticleResponse.Data?>?) : ListAdapter<NewArticleResponse.Data, NewRVAdapter.NewViewHolder>(NewDiffCallback) {
    private val itemList = _itemList!!
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):NewViewHolder {
        return NewViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface OnItemClickListener{
        fun onItemClick(v: View, position:Int)
    }

    fun setItemClickListener(onItemClickListener:OnItemClickListener){
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener : OnItemClickListener
    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.bind(itemList[position]!!)
    }

    class NewViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NewArticleResponse.Data) {
            with(binding){
                tvTitle.text = item.postTitle
                tvComment.text = item.postCommentCnt.toString()
                tvContent.text= item.postContent
                tvThumb.text = item.postLikeCnt.toString()
            }
        }
    }

    companion object {
        private val NewDiffCallback =
            DiffCallback<NewArticleResponse.Data>(id = { old, new -> old.postId == new.postId})
    }
}