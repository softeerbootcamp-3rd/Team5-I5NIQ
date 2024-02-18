package com.hyundai.myexperience.ui.reservation.program_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.AVANTE_N
import com.hyundai.myexperience.AVANTE_N_LINE
import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_SOLDOUT
import com.hyundai.myexperience.data.entity.CarDate
import com.hyundai.myexperience.data.entity.ReservationDate
import com.hyundai.myexperience.databinding.FragmentReservationCarDateBinding
import com.hyundai.myexperience.ui.reservation.adapter.CarDateAdapter

class ReservationCarDateFragment : Fragment() {
    private var _binding: FragmentReservationCarDateBinding? = null
    private val binding get() = _binding!!

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

        val dates = listOf(
            ReservationDate("02.25", RESERVATION_STATUS_ABLE),
            ReservationDate("02.26", RESERVATION_STATUS_SOLDOUT),
            ReservationDate("02.28", RESERVATION_STATUS_SOLDOUT),
            ReservationDate("03.02", RESERVATION_STATUS_ABLE),
            ReservationDate("03.06", RESERVATION_STATUS_ABLE)
        )

        val carDates = listOf(
            CarDate(AVANTE_N, dates),
            CarDate(AVANTE_N_LINE, dates)
        )

        initDateRecyclerView(carDates)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDateRecyclerView(carDates: List<CarDate>) {
        binding.rv.adapter = CarDateAdapter(carDates)
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
    }
}