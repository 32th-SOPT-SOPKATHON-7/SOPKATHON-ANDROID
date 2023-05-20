package com.solosol.a32th_sopt_sopkathon_7_android.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.solosol.a32th_sopt_sopkathon_7_android.api.model.response.DetailArticleResponse
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemCommentBinding
import com.solosol.a32th_sopt_sopkathon_7_android.util.DiffCallback
import com.solosol.a32th_sopt_sopkathon_7_android.util.TimeUtil


class CommentAdapter :
    ListAdapter<DetailArticleResponse.Data.Comment, CommentAdapter.CommentViewHolder>(
        CommentDiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(
            ItemCommentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class CommentViewHolder(private val binding: ItemCommentBinding) : ViewHolder(binding.root) {
        fun bind(item: DetailArticleResponse.Data.Comment) {
            binding.tvTextComment.text = item.content
            binding.tvCommentTime.text = TimeUtil.calculateTimeDifference(item.createdAt ?:"")
        }

        inner class CommentViewHolder(private val binding: ItemCommentBinding) :
            ViewHolder(binding.root) {
            fun bind(item: DetailArticleResponse.Data.Comment) {
                binding.tvTextComment.text = item.content
            }
        }

    }

    companion object {
        private val CommentDiffCallback =
            DiffCallback<DetailArticleResponse.Data.Comment>(id = { old, new -> old.commentId == new.commentId })
    }
}