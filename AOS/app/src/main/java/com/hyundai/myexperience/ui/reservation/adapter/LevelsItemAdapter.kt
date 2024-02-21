package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.reservation.LevelsItem
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.ReservationViewModel

class LevelsItemAdapter(
    private var levelsItems: List<LevelsItem>,
    private val viewModel: ReservationViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val startId: Int
) :
    RecyclerView.Adapter<LevelsItemViewHolder>() {
    private lateinit var binding: ItemLabelBoxBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelsItemViewHolder {
        initDataBinding(parent)
        initRecyclerView()

        return LevelsItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LevelsItemViewHolder, position: Int) {
        holder.bind(levelsItems[position], startId + position, viewModel) { notifyDataSetChanged() }
    }

    override fun getItemCount(): Int {
        return levelsItems.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemLabelBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.lifecycleOwner = lifecycleOwner

        binding.reservationProgramViewModel = viewModel
    }

    private fun initRecyclerView() {
        binding.rv.layoutManager = LinearLayoutManager(binding.rv.context)
    }

    fun setData(data: List<LevelsItem>) {
        levelsItems = data
        notifyDataSetChanged()
    }
}