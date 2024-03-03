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
import com.hyundai.myexperience.databinding.FragmentReservationCarDateBinding
import com.hyundai.myexperience.ui.common.createTooltip
import com.hyundai.myexperience.ui.reservation.ReservationViewModel
import com.hyundai.myexperience.ui.reservation.adapter.DatesItemAdapter
import com.skydoves.balloon.showAlignTop
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        setTooltip()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDateRecyclerView() {
        val adapter =
            DatesItemAdapter(reservationViewModel.carDates.value!!, reservationViewModel, this)

        reservationViewModel.carDates.observe(requireActivity()) {
            adapter.setData(it)
        }

        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setTooltip() {
        lifecycleScope.launch {
            delay(500L)
            binding.vTooltipPoint.showAlignTop(
                createTooltip(
                    requireContext(),
                    resources.getString(R.string.reservation_car_date_tooltip)
                )
            )
        }
    }
}