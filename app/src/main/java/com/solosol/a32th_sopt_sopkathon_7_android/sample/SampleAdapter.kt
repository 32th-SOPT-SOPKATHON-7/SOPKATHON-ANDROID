package com.solosol.a32th_sopt_sopkathon_7_android.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.solosol.a32th_sopt_sopkathon_7_android.databinding.ItemSampleBinding
import com.solosol.a32th_sopt_sopkathon_7_android.util.DiffCallback

/*

class SampleAdapter : ListAdapter<SampleData, SampleAdapter.SampleViewHolder>(SampleDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        return SampleViewHolder(ItemSampleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    class SampleViewHolder(private val binding: ItemSampleBinding) : ViewHolder(binding.root) {
        fun bind(item: SampleData) {

        }
    }

    companion object {
        private val SampleDiffCallback =
            DiffCallback<SampleData>(id = { old, new -> old.sample == new.sample})
    }
}*/
