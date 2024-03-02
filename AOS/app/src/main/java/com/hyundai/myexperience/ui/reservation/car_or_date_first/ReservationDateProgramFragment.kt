package com.hyundai.myexperience.ui.reservation.car_or_date_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.data.entity.reservation.ReservationDatesItem
import com.hyundai.myexperience.databinding.FragmentReservationDateProgramBinding
import com.hyundai.myexperience.ui.reservation.ReservationViewModel
import com.hyundai.myexperience.ui.reservation.adapter.DatesItemAdapter

class ReservationDateProgramFragment : Fragment() {
    private var _binding: FragmentReservationDateProgramBinding? = null
    private val binding get() = _binding!!

    private val reservationViewModel: ReservationViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationDateProgramBinding.inflate(inflater, container, false)
        initDataBinding()

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

    private fun initDataBinding() {
        binding.lifecycleOwner = this
        binding.reservationViewModel = reservationViewModel
    }

    private fun initDateRecyclerView() {
        val adapter = DatesItemAdapter(
            listOf(
                ReservationDatesItem(
                    "날짜",
                    reservationViewModel.dates.value!!
                )
            ), reservationViewModel, this
        )

        reservationViewModel.dates.observe(requireActivity()) {
            if (reservationViewModel.dates.value != null) {
                adapter.setData(
                    listOf(
                        ReservationDatesItem(
                            "날짜",
                            reservationViewModel.dates.value!!
                        )
                    )
                )
            }
        }

        binding.rvDate.adapter = adapter
        binding.rvDate.layoutManager = LinearLayoutManager(requireContext())
    }
}