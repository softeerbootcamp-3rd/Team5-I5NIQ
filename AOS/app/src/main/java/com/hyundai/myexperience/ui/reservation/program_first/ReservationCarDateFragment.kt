package com.hyundai.myexperience.ui.reservation.program_first

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.RESERVATION_STATUS_ABLE
import com.hyundai.myexperience.RESERVATION_STATUS_SOLDOUT
import com.hyundai.myexperience.data.entity.ReservationDate
import com.hyundai.myexperience.databinding.FragmentReservationCarDateBinding
import com.hyundai.myexperience.ui.reservation.adapter.ReservationDateAdapter
import com.hyundai.myexperience.ui.reservation.listener.DateClickListener

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

        initCardViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCardViews() {
        val clicked = mutableListOf(false, false)

        val white = ContextCompat.getColor(requireContext(), R.color.white)
        val gray2 = ContextCompat.getColor(requireContext(), R.color.gray2)

        val dateList = listOf(
            ReservationDate("02.25", RESERVATION_STATUS_ABLE),
            ReservationDate("02.26", RESERVATION_STATUS_SOLDOUT),
            ReservationDate("02.28", RESERVATION_STATUS_SOLDOUT),
            ReservationDate("03.02", RESERVATION_STATUS_ABLE),
            ReservationDate("03.06", RESERVATION_STATUS_ABLE)
        )

        binding.rvAvanteN.adapter = ReservationDateAdapter(dateList, object : DateClickListener {
            override fun onDateClick(date: String) {
                binding.tvAvanteNSelected.text = date
                binding.cvAvanteN.callOnClick()
            }
        })
        binding.rvAvanteN.layoutManager = GridLayoutManager(requireContext(), 4)

        binding.rvAvanteNLine.adapter =
            ReservationDateAdapter(dateList, object : DateClickListener {
                override fun onDateClick(date: String) {
                    binding.tvAvanteNLineSelected.text = date
                    binding.cvAvanteNLine.callOnClick()
                }
            })
        binding.rvAvanteNLine.layoutManager = GridLayoutManager(requireContext(), 4)

        binding.cvAvanteN.setOnClickListener {
            if (clicked[0]) {
                binding.cvAvanteN.strokeColor = gray2
                binding.tvAvanteN.setTextColor(gray2)
                binding.tvAvanteNSelected.setTextColor(gray2)
                binding.ivArrowAvanteN.imageTintList = ColorStateList.valueOf(gray2)
                binding.ivArrowAvanteN.rotation = 0f
                binding.rvAvanteN.visibility = View.GONE
            } else {
                binding.cvAvanteN.strokeColor = white
                binding.tvAvanteN.setTextColor(white)
                binding.tvAvanteNSelected.setTextColor(white)
                binding.ivArrowAvanteN.imageTintList = ColorStateList.valueOf(white)
                binding.ivArrowAvanteN.rotation = 180f
                binding.rvAvanteN.visibility = View.VISIBLE
            }

            clicked[0] = !clicked[0]
        }

        binding.cvAvanteNLine.setOnClickListener {
            if (clicked[1]) {
                binding.cvAvanteNLine.strokeColor = gray2
                binding.tvAvanteNLine.setTextColor(gray2)
                binding.tvAvanteNLineSelected.setTextColor(gray2)
                binding.ivArrowAvanteNLine.imageTintList = ColorStateList.valueOf(gray2)
                binding.ivArrowAvanteNLine.rotation = 0f
                binding.rvAvanteNLine.visibility = View.GONE
            } else {
                binding.cvAvanteNLine.strokeColor = white
                binding.tvAvanteNLine.setTextColor(white)
                binding.tvAvanteNLineSelected.setTextColor(white)
                binding.ivArrowAvanteNLine.imageTintList = ColorStateList.valueOf(white)
                binding.ivArrowAvanteNLine.rotation = 180f
                binding.rvAvanteNLine.visibility = View.VISIBLE
            }

            clicked[1] = !clicked[1]
        }

    }
}