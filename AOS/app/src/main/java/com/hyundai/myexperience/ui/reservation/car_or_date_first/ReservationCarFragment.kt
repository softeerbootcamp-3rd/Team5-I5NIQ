package com.hyundai.myexperience.ui.reservation.car_or_date_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.reservation.LevelsItem
import com.hyundai.myexperience.data.entity.reservation.ReservationDatesItem
import com.hyundai.myexperience.databinding.FragmentReservationCarBinding
import com.hyundai.myexperience.ui.reservation.ReservationViewModel
import com.hyundai.myexperience.ui.reservation.adapter.DatesItemAdapter
import com.hyundai.myexperience.ui.reservation.adapter.LevelsItemAdapter

class ReservationCarFragment : Fragment() {
    private var _binding: FragmentReservationCarBinding? = null
    private val binding get() = _binding!!

    private val reservationViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationCarBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCarRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initCarRecyclerView() {
        val adapter =
            DatesItemAdapter(reservationViewModel.carDates.value!!, reservationViewModel, this)

        reservationViewModel.carDates.observe(requireActivity()) {
            adapter.setData(it)
        }

        binding.rvCar.adapter = adapter
        binding.rvCar.layoutManager = LinearLayoutManager(requireContext())
    }
}