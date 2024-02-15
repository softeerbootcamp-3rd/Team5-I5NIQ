package com.hyundai.myexperience.ui.reservation.program_first

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentReservationProgramBinding

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

        var clicked = false

        val white = ContextCompat.getColor(requireContext(), R.color.white)
        val gray2 = ContextCompat.getColor(requireContext(), R.color.gray2)

        binding.cvHyundai.setOnClickListener {
            if (clicked) {
                binding.cvHyundai.strokeColor = gray2
                binding.tvHyundai.setTextColor(gray2)
                binding.ivArrowHyundai.imageTintList = ColorStateList.valueOf(gray2)
                binding.ivArrowHyundai.rotation = 0f
            } else {
                binding.cvHyundai.strokeColor = white
                binding.tvHyundai.setTextColor(white)
                binding.ivArrowHyundai.imageTintList = ColorStateList.valueOf(white)
                binding.ivArrowHyundai.rotation = 180f
            }

            clicked = !clicked
        }

        binding.cvKia.setOnClickListener {

        }

        binding.cvGenesis.setOnClickListener {

        }

        binding.cvHmgExperience.setOnClickListener {

        }

        binding.cvTaxi.setOnClickListener {

        }

        binding.cvHmgPleasure.setOnClickListener {

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}