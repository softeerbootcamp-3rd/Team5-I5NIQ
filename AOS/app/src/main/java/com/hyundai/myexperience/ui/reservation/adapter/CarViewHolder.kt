package com.hyundai.myexperience.ui.reservation.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.data.entity.reservation.ReservationCar
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.ReservationViewModel

class CarViewHolder(private val binding: ItemLabelBoxBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        car: ReservationCar,
        idx: Int,
        viewModel: ReservationViewModel,
        notify: () -> Unit
    ) {
        binding.tvTitle.text = car.name
        binding.tvStatus.text = car.status

        if (idx != viewModel.openedProgramIdx.value) {
            setColorByCondition(binding, false)
        } else {
            setColorByCondition(binding, true)
        }

        if (car.status == RESERVATION_STATUS_UNABLE) {
            setColorByCondition(binding, true, false)
        } else {
            binding.mcv.setOnClickListener {
                if (idx != viewModel.openedProgramIdx.value) {
                    viewModel.setOpenedProgramIdx(idx)
                    viewModel.setSelectedCar(car.name)
                } else {
                    viewModel.setOpenedProgramIdx(-1)
                    viewModel.setSelectedCar("")
                }

                notify()
            }
        }
    }

    private fun setColorByCondition(
        binding: ItemLabelBoxBinding,
        selected: Boolean,
        enabled: Boolean = true
    ) {
        val white = ContextCompat.getColor(binding.rv.context, R.color.white)
        val gray = ContextCompat.getColor(binding.rv.context, R.color.gray2)
        val darkGray = ContextCompat.getColor(binding.rv.context, R.color.gray3)

        if (!enabled) {
            setLabelBoxColor(darkGray)
        } else if (selected) {
            setLabelBoxColor(white)
        } else {
            setLabelBoxColor(gray)
        }
    }

    private fun setLabelBoxColor(color: Int) {
        binding.mcv.strokeColor = color
        binding.tvTitle.setTextColor(color)
        binding.tvStatus.setTextColor(color)
    }
}