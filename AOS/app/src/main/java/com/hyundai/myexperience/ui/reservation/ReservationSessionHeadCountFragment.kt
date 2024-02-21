package com.hyundai.myexperience.ui.reservation

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.ReservationDatesItem
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

        val editableFactory = Editable.Factory.getInstance()

        binding.btnMinus.setOnClickListener {
            val cnt = binding.etHeadcount.text.toString().toInt()
            binding.etHeadcount.text = editableFactory.newEditable(max(1, cnt - 1).toString())
        }

        binding.btnPlus.setOnClickListener {
            val cnt = binding.etHeadcount.text.toString().toInt()
            binding.etHeadcount.text = editableFactory.newEditable(min(9, cnt + 1).toString())
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
        val adapter = DatesItemAdapter(listOf(ReservationDatesItem("회차", reservationViewModel.sessions.value!!)), reservationViewModel, this)

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

    private fun setParticipationBtn() {
        var participation = true
        binding.btnParticipation.setOnClickListener {
            if (!participation) {
                participation = true
                setParticipationBtnColor(true)
            }
        }

        binding.btnNonParticipation.setOnClickListener {
            if (participation) {
                participation = false
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