package com.hyundai.myexperience.ui.reservation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.CarDate
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.listener.DateClickListener
import com.hyundai.myexperience.ui.reservation.listener.LabelBoxClickListener

class CarDateViewHolder(private val binding: ItemLabelBoxBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        carDate: CarDate,
        position: Int,
        openedIdx: Int,
        selectedCar: String,
        listener: LabelBoxClickListener
    ) {
        binding.tvTitle.text = carDate.car

        if (position != openedIdx) {
            setUnfocusedCard()
        } else {
            setFocusedCard()
        }

        if (carDate.car == selectedCar) {
            setSelectedSubTitle()
        } else {
            setUnselectedSubTitle()
        }

        binding.mcv.setOnClickListener {
            listener.onBoxClick(position)
        }

        binding.rv.adapter = ReservationDateAdapter(carDate.dates, object : DateClickListener {
            override fun onDateClick(date: String) {
                listener.onItemClick(carDate.car, date)

                binding.tvSubtitle.text = date
            }
        })
    }

    private fun setUnfocusedCard() {
        binding.rv.visibility = View.GONE
        binding.tvSubtitle.visibility = View.INVISIBLE
        binding.ivIcon.rotation = 0f

        setColor(binding, false)
    }

    private fun setFocusedCard() {
        if (binding.rv.visibility != View.VISIBLE) {
            binding.rv.visibility = View.VISIBLE
            binding.ivIcon.rotation = 180f

            setColor(binding, true)
        } else {
            binding.rv.visibility = View.GONE
            binding.ivIcon.rotation = 0f
        }
    }

    private fun setSelectedSubTitle() {
        binding.tvSubtitle.visibility = View.VISIBLE
    }

    private fun setUnselectedSubTitle() {
        binding.tvSubtitle.visibility = View.INVISIBLE
    }

    private fun setColor(binding: ItemLabelBoxBinding, selected: Boolean) {
        if (selected) {
            binding.mcv.strokeColor = ContextCompat.getColor(binding.rv.context, R.color.white)
            binding.tvTitle.setTextColor(ContextCompat.getColor(binding.rv.context, R.color.white))
            binding.tvSubtitle.setTextColor(
                ContextCompat.getColor(
                    binding.rv.context,
                    R.color.white
                )
            )
        } else {
            binding.mcv.strokeColor = ContextCompat.getColor(binding.rv.context, R.color.gray2)
            binding.tvTitle.setTextColor(ContextCompat.getColor(binding.rv.context, R.color.gray2))
            binding.tvSubtitle.setTextColor(
                ContextCompat.getColor(
                    binding.rv.context,
                    R.color.gray2
                )
            )
        }
    }
}