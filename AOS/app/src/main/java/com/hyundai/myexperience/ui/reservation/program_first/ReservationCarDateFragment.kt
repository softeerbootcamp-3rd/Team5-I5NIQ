package com.hyundai.myexperience.ui.reservation.program_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.databinding.FragmentReservationCarDateBinding
import com.hyundai.myexperience.ui.reservation.adapter.DatesItemAdapter

class ReservationCarDateFragment : Fragment() {
    private var _binding: FragmentReservationCarDateBinding? = null
    private val binding get() = _binding!!

    private val reservationViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationCarDateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initDateRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDateRecyclerView() {
        val adapter = DatesItemAdapter(reservationViewModel.carDates.value!!)
        reservationViewModel.requestCarDates()

        reservationViewModel.carDates.observe(requireActivity()) {
            adapter.setData(it)
        }

        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
    }
}