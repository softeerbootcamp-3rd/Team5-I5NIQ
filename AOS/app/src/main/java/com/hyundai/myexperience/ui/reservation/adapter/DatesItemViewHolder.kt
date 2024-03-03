package com.hyundai.myexperience.ui.reservation.adapter

import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.reservation.ReservationDatesItem
import com.hyundai.myexperience.databinding.ItemCarDateBinding
import com.hyundai.myexperience.ui.reservation.ReservationViewModel
import com.hyundai.myexperience.ui.reservation.listener.DateClickListener

class DatesItemViewHolder(private val binding: ItemCarDateBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        reservationDatesItem: ReservationDatesItem,
        idx: Int,
        viewModel: ReservationViewModel,
        notify: () -> Unit
    ) {
        binding.tvTitle.text = reservationDatesItem.title

        if (idx != viewModel.openedCarDateIdx.value) {
            setUnfocusedCard()
        } else {
            setFocusedCard(reservationDatesItem.dates.isEmpty())
        }

        if (reservationDatesItem.title == viewModel.selectedCar.value) {
            setSelectedSubTitle()
        } else {
            setUnselectedSubTitle()
        }

        if (viewModel.step.value == 2) {
            setSelectedSubTitle()
        }

        binding.mcv.setOnClickListener {
            viewModel.setOpenedCarDateIdx(idx)
            notify()
        }

        binding.rv.adapter = DateAdapter(reservationDatesItem.dates, object : DateClickListener {
            override fun onDateClick(date: String, id: Int, cost: Int, maxHeadCount: Int) {
                when (viewModel.type.value) {
                    0 -> {
                        if (viewModel.step.value == 1) {
                            viewModel.setSelectedCar(reservationDatesItem.title)
                            viewModel.setSelectedCarId(id)
                            viewModel.setSelectedDate(date)
                        }
                    }

                    1 -> {
                        if (viewModel.step.value == 0) {
                            viewModel.setSelectedDate(date)

                            viewModel.requestExperienceProgramsByDate()
                            viewModel.requestPleasureProgramsByDate()
                        }
                    }
                }

                if (viewModel.step.value == 2){
                    viewModel.setSelectedClassId(id)
                    viewModel.setSelectedSession(date)
                    viewModel.setSelectedCost(cost)
                    viewModel.setSelectedMaxHeadCount(maxHeadCount)
                }

                notify()
            }
        })
    }

    private fun setUnfocusedCard() {
        binding.rv.visibility = View.GONE
        binding.tvSubtitle.visibility = View.INVISIBLE
        binding.ivIcon.rotation = 0f

        setColor(binding, false)
    }

    private fun setFocusedCard(isEmpty: Boolean) {
        if (binding.rv.visibility != View.VISIBLE) {
            if (!isEmpty) {
                binding.rv.visibility = View.VISIBLE
                binding.ivIcon.rotation = 180f
            }

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

    private fun setColor(binding: ItemCarDateBinding, selected: Boolean) {
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