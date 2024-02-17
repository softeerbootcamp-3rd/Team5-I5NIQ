package com.hyundai.myexperience.ui.reservation.program_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
import com.hyundai.myexperience.data.entity.Program
import com.hyundai.myexperience.databinding.FragmentReservationProgramBinding
import com.hyundai.myexperience.ui.reservation.adapter.ProgramAdapter

class ReservationProgramFragment : Fragment() {
    private var _binding: FragmentReservationProgramBinding? = null
    private val binding get() = _binding!!

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

        val levels = listOf(
            Level(PROGRAM_LEVEL_1, RESERVATION_STATUS_ABLE),
            Level(PROGRAM_LEVEL_2, RESERVATION_STATUS_ABLE),
            Level(PROGRAM_LEVEL_3, RESERVATION_STATUS_UNABLE),
            Level(PROGRAM_OFF_ROAD, RESERVATION_STATUS_ABLE)
        )

        val experiencePrograms = listOf(
            Program(COMPANY_HYUNDAI, levels),
            Program(COMPANY_KIA, levels),
            Program(COMPANY_GENESIS, levels),
            Program(COMPANY_HMG, levels)
        )

        val pleasurePrograms = listOf(
            Program(TYPE_TAXI, levels),
            Program(COMPANY_HMG, levels),
        )

        initExperienceRecyclerView(experiencePrograms)
        initPleasureRecyclerView(pleasurePrograms)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initExperienceRecyclerView(programs: List<Program>) {
        binding.rvExperience.adapter = ProgramAdapter(programs)
        binding.rvExperience.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initPleasureRecyclerView(programs: List<Program>) {
        binding.rvPleasure.adapter = ProgramAdapter(programs)
        binding.rvPleasure.layoutManager = LinearLayoutManager(requireContext())
    }
}