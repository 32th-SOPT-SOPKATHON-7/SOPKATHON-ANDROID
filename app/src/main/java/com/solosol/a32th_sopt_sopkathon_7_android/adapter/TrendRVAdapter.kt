package com.solosol.a32th_sopt_sopkathon_7_android.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemPostBinding
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemSampleBinding
import com.solosol.a32th_sopt_sopkathon_7_android.sample.SampleData
import com.solosol.a32th_sopt_sopkathon_7_android.util.DiffCallback

class TrendRVAdapter : ListAdapter<SampleData, TrendRVAdapter.TrendViewHolder>(SampleDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):TrendViewHolder {
        return TrendViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TrendViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class TrendViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SampleData) {

        }
    }

    companion object {
        private val SampleDiffCallback =
            DiffCallback<SampleData>(id = { old, new -> old.sample == new.sample})
    }
}