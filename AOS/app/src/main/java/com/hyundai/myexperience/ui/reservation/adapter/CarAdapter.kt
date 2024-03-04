package com.hyundai.myexperience.ui.reservation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.data.entity.reservation.ReservationCar
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.ReservationViewModel

class CarAdapter(
    private var reservationCars: List<ReservationCar>,
    private val viewModel: ReservationViewModel,
    private val lifecycleOwner: LifecycleOwner,
) :
    RecyclerView.Adapter<CarViewHolder>() {
    private lateinit var binding: ItemLabelBoxBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        initDataBinding(parent)

        binding.ivIcon.visibility = View.INVISIBLE
        binding.tvTitle.visibility = View.VISIBLE
        binding.tvStatus.visibility = View.VISIBLE

        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(reservationCars[position], position, viewModel) { notifyDataSetChanged() }
    }

    override fun getItemCount(): Int {
        return reservationCars.size
    }

    private fun initDataBinding(parent: ViewGroup) {
        binding = ItemLabelBoxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.lifecycleOwner = lifecycleOwner

        binding.reservationProgramViewModel = viewModel
    }

    fun setData(data: List<ReservationCar>) {
        reservationCars = data
        notifyDataSetChanged()
    }
}