package com.hyundai.myexperience.ui.reservation

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
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
import com.hyundai.myexperience.databinding.FragmentReservationSessionHeadcountBinding
import com.hyundai.myexperience.ui.reservation.adapter.ReservationDateAdapter
import com.hyundai.myexperience.ui.reservation.listener.DateClickListener
import kotlin.math.max
import kotlin.math.min

class ReservationSessionHeadCountFragment : Fragment() {
    private var _binding: FragmentReservationSessionHeadcountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationSessionHeadcountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCardViews()

        val editableFactory = Editable.Factory.getInstance()

        binding.btnMinus.setOnClickListener {
            val cnt = binding.etHeadcount.text.toString().toInt()
            binding.etHeadcount.text = editableFactory.newEditable(max(1, cnt - 1).toString())
        }

        binding.btnPlus.setOnClickListener {
            val cnt = binding.etHeadcount.text.toString().toInt()
            binding.etHeadcount.text = editableFactory.newEditable(min(9, cnt + 1).toString())
        }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCardViews() {
        val clicked = mutableListOf(false)

        val white = ContextCompat.getColor(requireContext(), R.color.white)
        val gray2 = ContextCompat.getColor(requireContext(), R.color.gray2)

        val dateList = listOf(
            ReservationDate("08:30", RESERVATION_STATUS_ABLE),
            ReservationDate("09:30", RESERVATION_STATUS_SOLDOUT),
            ReservationDate("10:30", RESERVATION_STATUS_SOLDOUT),
            ReservationDate("13:30", RESERVATION_STATUS_ABLE),
        )

        binding.rvSession.adapter = ReservationDateAdapter(dateList, object : DateClickListener {
            override fun onLevelClick(date: String) {
                binding.tvSessionSelected.text = date
                binding.cvSession.callOnClick()
            }
        })
        binding.rvSession.layoutManager = GridLayoutManager(requireContext(), 4)

        binding.cvSession.setOnClickListener {
            if (clicked[0]) {
                binding.cvSession.strokeColor = gray2
                binding.tvSession.setTextColor(gray2)
                binding.tvSessionSelected.setTextColor(gray2)
                binding.ivArrowSession.imageTintList = ColorStateList.valueOf(gray2)
                binding.ivArrowSession.rotation = 0f
                binding.rvSession.visibility = View.GONE
            } else {
                binding.cvSession.strokeColor = white
                binding.tvSession.setTextColor(white)
                binding.tvSessionSelected.setTextColor(white)
                binding.ivArrowSession.imageTintList = ColorStateList.valueOf(white)
                binding.ivArrowSession.rotation = 180f
                binding.rvSession.visibility = View.VISIBLE
            }

            clicked[0] = !clicked[0]
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