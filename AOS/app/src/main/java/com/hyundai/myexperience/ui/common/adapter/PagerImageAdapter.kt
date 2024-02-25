package com.hyundai.myexperience.ui.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.databinding.ItemImageBinding

class PagerImageAdapter(private val images: List<Int>) :
    RecyclerView.Adapter<PagerImageAdapter.PagerImageViewHolder>() {
    private lateinit var binding: ItemImageBinding

    class PagerImageViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Int) {
            binding.ivMain.setImageResource(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerImageViewHolder {
        binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PagerImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int = images.size
}
