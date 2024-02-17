package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.Program
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener

class ProgramAdapter(
    private val programs: List<Program>,
    private val levelClickListener: LevelClickListener,
    private val layoutManager: LinearLayoutManager
) :
    RecyclerView.Adapter<ProgramViewHolder>() {
    private lateinit var binding: ItemLabelBoxBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProgramViewHolder {
        initDataBinding(parent)

        return ProgramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProgramViewHolder, position: Int) {
        holder.bind(programs[position], levelClickListener, layoutManager)
    }

    override fun getItemCount(): Int {
        return programs.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemLabelBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }
}