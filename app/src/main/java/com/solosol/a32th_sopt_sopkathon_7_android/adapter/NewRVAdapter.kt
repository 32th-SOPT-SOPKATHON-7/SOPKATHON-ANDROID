package com.solosol.a32th_sopt_sopkathon_7_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemPostBinding
import com.solosol.a32th_sopt_sopkathon_7_android.sample.SampleData
import com.solosol.a32th_sopt_sopkathon_7_android.util.DiffCallback

class NewRVAdapter : ListAdapter<SampleData, NewRVAdapter.NewViewHolder>(SampleDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):NewViewHolder {
        return NewViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class NewViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SampleData) {

        }
    }

    companion object {
        private val SampleDiffCallback =
            DiffCallback<SampleData>(id = { old, new -> old.sample == new.sample})
    }
}