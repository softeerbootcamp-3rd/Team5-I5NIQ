package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.Program
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener

class ProgramAdapter(
    private val programs: List<Program>
) :
    RecyclerView.Adapter<ProgramViewHolder>() {
    private lateinit var binding: ItemLabelBoxBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        initDataBinding(parent)
        binding.rv.layoutManager = LinearLayoutManager(binding.rv.context)
        binding.rv.adapter = LevelAdapter(emptyList(), object : LevelClickListener {
            override fun onLevelClick(level: String) {
                binding.tvSubtitle.text = level
            }
        })

        return ProgramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(programs[position])
    }

    override fun getItemCount(): Int {
        return programs.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemLabelBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }
}