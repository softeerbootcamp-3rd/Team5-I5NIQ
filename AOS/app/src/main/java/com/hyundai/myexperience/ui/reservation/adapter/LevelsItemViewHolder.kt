package com.hyundai.myexperience.ui.reservation.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.reservation.LevelsItem
import com.hyundai.myexperience.databinding.ItemLabelBoxBinding
import com.hyundai.myexperience.ui.reservation.ReservationViewModel
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener

class LevelsItemViewHolder(private val binding: ItemLabelBoxBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        levelsItem: LevelsItem,
        idx: Int,
        viewModel: ReservationViewModel,
        notify: () -> Unit
    ) {
        binding.tvTitle.text = levelsItem.title

        if (idx != viewModel.openedProgramIdx.value) {
            setUnfocusedCard()
        } else {
            setFocusedCard(levelsItem.levels.isEmpty())
        }

        if (levelsItem.title == viewModel.selectedCompany.value) {
            setSelectedSubTitle()
        } else {
            setUnselectedSubTitle()
        }

        binding.mcv.setOnClickListener {
            viewModel.setOpenedProgramIdx(idx)
            notify()
        }

        binding.rv.adapter = LevelAdapter(levelsItem.levels, object : LevelClickListener {
            override fun onLevelClick(level: String, id: Int) {
                viewModel.setSelectedCompany(levelsItem.title)
                viewModel.setSelectedLevel(level)
                viewModel.setSelectedProgramId(id)
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