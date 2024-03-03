package com.hyundai.myexperience.ui.reservation.program_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentReservationProgramBinding
import com.hyundai.myexperience.ui.common.createTooltip
import com.hyundai.myexperience.ui.reservation.ReservationViewModel
import com.hyundai.myexperience.ui.reservation.adapter.LevelsItemAdapter
import com.skydoves.balloon.showAlignTop
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReservationProgramFragment : Fragment() {
    private var _binding: FragmentReservationProgramBinding? = null
    private val binding get() = _binding!!

    private val reservationViewModel: ReservationViewModel by activityViewModels()

    private var toolTipEnabled = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initExperienceRecyclerView()
        initPleasureRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        if (toolTipEnabled) {
            toolTipEnabled = false
            setTooltip()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initExperienceRecyclerView() {
        val adapter = LevelsItemAdapter(
            reservationViewModel.experiencePrograms.value!!,
            reservationViewModel,
            this,
            0
        )

        binding.rvExperience.adapter = adapter
        binding.rvExperience.layoutManager = LinearLayoutManager(requireContext())

        reservationViewModel.experiencePrograms.observe(requireActivity()) {
            adapter.setData(it)
        }

        reservationViewModel.openedProgramIdx.observe(requireActivity()) {
            adapter.notifyDataSetChanged()
        }
    }

    private fun initPleasureRecyclerView() {
        val adapter = LevelsItemAdapter(
            reservationViewModel.pleasurePrograms.value!!,
            reservationViewModel,
            this,
            10
        )

        binding.rvPleasure.adapter = adapter
        binding.rvPleasure.layoutManager = LinearLayoutManager(requireContext())

        reservationViewModel.pleasurePrograms.observe(requireActivity()) {
            adapter.setData(it)
        }

        reservationViewModel.openedProgramIdx.observe(requireActivity()) {
            adapter.notifyDataSetChanged()
        }
    }

    private fun setTooltip() {
        lifecycleScope.launch {
            delay(500L)
            binding.vTooltipPoint.showAlignTop(
                createTooltip(
                    requireContext(),
                    resources.getString(R.string.reservation_program_tooltip)
                )
            )
        }
    }
}