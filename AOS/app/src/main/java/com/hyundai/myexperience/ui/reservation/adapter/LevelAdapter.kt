package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.reservation.Level
import com.hyundai.myexperience.databinding.ItemProgramLevelBinding
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener

class LevelAdapter(
    private var levels: List<Level>,
    private val levelClickListener: LevelClickListener
) :
    RecyclerView.Adapter<LevelViewHolder>() {
    private lateinit var binding: ItemProgramLevelBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        initDataBinding(parent)

        return LevelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(levels[position], levelClickListener)
    }

    override fun getItemCount(): Int {
        return levels.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding =
            ItemProgramLevelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    fun updateData(newLevels: List<Level>) {
        levels = newLevels
        notifyDataSetChanged()
    }
}