package com.hyundai.myexperience.ui.reservation.program_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.databinding.FragmentReservationProgramBinding
import com.hyundai.myexperience.ui.reservation.adapter.LevelsItemAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationProgramFragment : Fragment() {
    private var _binding: FragmentReservationProgramBinding? = null
    private val binding get() = _binding!!

    private val reservationProgramViewModel: ReservationProgramViewModel by activityViewModels()

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
        val adapter = LevelsItemAdapter(
            reservationProgramViewModel.experiencePrograms.value!!,
            reservationProgramViewModel,
            this,
            0
        )
        reservationProgramViewModel.requestExperiencePrograms()

        binding.rvExperience.adapter = adapter
        binding.rvExperience.layoutManager = LinearLayoutManager(requireContext())

        reservationProgramViewModel.experiencePrograms.observe(requireActivity()) {
            adapter.setData(it)
        }

        reservationProgramViewModel.openedIdx.observe(requireActivity()) {
            adapter.notifyDataSetChanged()
        }
    }

    private fun initPleasureRecyclerView() {
        val adapter = LevelsItemAdapter(
            reservationProgramViewModel.pleasurePrograms.value!!,
            reservationProgramViewModel,
            this,
            10
        )
        reservationProgramViewModel.requestPleasurePrograms()

        binding.rvPleasure.adapter = adapter
        binding.rvPleasure.layoutManager = LinearLayoutManager(requireContext())

        reservationProgramViewModel.pleasurePrograms.observe(requireActivity()) {
            adapter.setData(it)
        }

        reservationProgramViewModel.openedIdx.observe(requireActivity()) {
            adapter.notifyDataSetChanged()
        }
    }
}