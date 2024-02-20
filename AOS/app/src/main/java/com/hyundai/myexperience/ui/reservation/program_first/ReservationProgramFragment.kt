package com.hyundai.myexperience.ui.reservation.program_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.COMPANY_GENESIS
import com.hyundai.myexperience.COMPANY_HMG
import com.hyundai.myexperience.COMPANY_HYUNDAI
import com.hyundai.myexperience.COMPANY_KIA
import com.hyundai.myexperience.PROGRAM_LEVEL_1
import com.hyundai.myexperience.PROGRAM_LEVEL_2
import com.hyundai.myexperience.PROGRAM_LEVEL_3
import com.hyundai.myexperience.PROGRAM_OFF_ROAD
import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_UNABLE
import com.hyundai.myexperience.TYPE_TAXI
import com.hyundai.myexperience.data.entity.Level
import com.hyundai.myexperience.data.entity.LevelsItem
import com.hyundai.myexperience.databinding.FragmentReservationProgramBinding
import com.hyundai.myexperience.ui.reservation.adapter.LevelsItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationProgramFragment : Fragment() {
    private var _binding: FragmentReservationProgramBinding? = null
    private val binding get() = _binding!!

    private val reservationProgramViewModel: ReservationProgramViewModel by viewModels()

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initExperienceRecyclerView() {
        val adapter = LevelsItemAdapter(reservationProgramViewModel.experiencePrograms.value!!)
        reservationProgramViewModel.requestExperiencePrograms()

        binding.rvExperience.adapter = adapter
        binding.rvExperience.layoutManager = LinearLayoutManager(requireContext())

        reservationProgramViewModel.experiencePrograms.observe(requireActivity()) {
            adapter.setData(it)
        }
    }

    private fun initPleasureRecyclerView() {
        val adapter = LevelsItemAdapter(reservationProgramViewModel.pleasurePrograms.value!!)
        reservationProgramViewModel.requestPleasurePrograms()

        binding.rvPleasure.adapter = adapter
        binding.rvPleasure.layoutManager = LinearLayoutManager(requireContext())

        reservationProgramViewModel.pleasurePrograms.observe(requireActivity()) {
            adapter.setData(it)
        }
    }
}