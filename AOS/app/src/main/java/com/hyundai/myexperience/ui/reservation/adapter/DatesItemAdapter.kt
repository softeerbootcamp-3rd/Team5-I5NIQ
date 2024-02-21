package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.ReservationDatesItem
import com.hyundai.myexperience.databinding.ItemCarDateBinding
import com.hyundai.myexperience.ui.reservation.ReservationViewModel

class DatesItemAdapter(
    private var reservationDatesItems: List<ReservationDatesItem>,
    private val viewModel: ReservationViewModel,
    private val lifecycleOwner: LifecycleOwner,
) :
    RecyclerView.Adapter<DatesItemViewHolder>() {
    private lateinit var binding: ItemCarDateBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatesItemViewHolder {
        initDataBinding(parent)
        initRecyclerView()

        return DatesItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DatesItemViewHolder, position: Int) {
        holder.bind(reservationDatesItems[position], position, viewModel) { notifyDataSetChanged() }
    }

    override fun getItemCount(): Int {
        return reservationDatesItems.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemCarDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.lifecycleOwner = lifecycleOwner

        binding.reservationProgramViewModel = viewModel
    }

    private fun initRecyclerView() {
        binding.rv.layoutManager = GridLayoutManager(binding.rv.context, 4)
    }

    fun setData(data: List<ReservationDatesItem>) {
        reservationDatesItems = data
        notifyDataSetChanged()
    }
}