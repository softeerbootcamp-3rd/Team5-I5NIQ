package com.hyundai.myexperience.ui.reservation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.reservation.ReservationDatesItem
import com.hyundai.myexperience.databinding.FragmentReservationSessionHeadcountBinding
import com.hyundai.myexperience.ui.reservation.adapter.DatesItemAdapter
import kotlin.math.max
import kotlin.math.min

class ReservationSessionHeadCountFragment : Fragment() {
    private var _binding: FragmentReservationSessionHeadcountBinding? = null
    private val binding get() = _binding!!

    private val reservationViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationSessionHeadcountBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSessionRecyclerView()

        binding.btnMinus.setOnClickListener {
            onClickMinusBtn()
        }

        binding.btnPlus.setOnClickListener {
            onClickPlusBtn()
        }

        setParticipationBtn()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this
        binding.reservationViewModel = reservationViewModel
    }

    private fun initSessionRecyclerView() {
        val adapter = DatesItemAdapter(
            listOf(
                ReservationDatesItem(
                    "회차",
                    reservationViewModel.sessions.value!!
                )
            ), reservationViewModel, this
        )

        reservationViewModel.sessions.observe(requireActivity()) {
            if (reservationViewModel.sessions.value != null) {
                adapter.setData(
                    listOf(
                        ReservationDatesItem(
                            "회차",
                            reservationViewModel.sessions.value!!
                        )
                    )
                )
            }
        }

        binding.rvSession.adapter = adapter
        binding.rvSession.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onClickMinusBtn() {
        val cnt = binding.etHeadcount.text.toString().toInt()
        val newCnt = max(1, cnt - 1)

        reservationViewModel.setSelectedHeadCount(newCnt)
    }

    private fun onClickPlusBtn() {
        val cnt = binding.etHeadcount.text.toString().toInt()
        val newCnt = min(reservationViewModel.selectedMaxHeadCount.value!!, cnt + 1)

        reservationViewModel.setSelectedHeadCount(newCnt)
    }

    private fun setParticipationBtn() {
        binding.btnParticipation.setOnClickListener {
            if (!reservationViewModel.participation.value!!) {
                reservationViewModel.setParticipation(true)
                setParticipationBtnColor(true)
            }
        }

        binding.btnNonParticipation.setOnClickListener {
            if (reservationViewModel.participation.value!!) {
                reservationViewModel.setParticipation(false)
                setParticipationBtnColor(false)
            }
        }
    }

    private fun setParticipationBtnColor(participation: Boolean) {
        val white = ContextCompat.getColor(requireContext(), R.color.white)
        val gray2 = ContextCompat.getColor(requireContext(), R.color.gray2)

        if (participation) {
            binding.btnParticipation.strokeColor = ColorStateList.valueOf(white)
            binding.btnParticipation.setTextColor(white)

            binding.btnNonParticipation.strokeColor = ColorStateList.valueOf(gray2)
            binding.btnNonParticipation.setTextColor(gray2)
        } else {
            binding.btnParticipation.strokeColor = ColorStateList.valueOf(gray2)
            binding.btnParticipation.setTextColor(gray2)

            binding.btnNonParticipation.strokeColor = ColorStateList.valueOf(white)
            binding.btnNonParticipation.setTextColor(white)
        }
    }
}