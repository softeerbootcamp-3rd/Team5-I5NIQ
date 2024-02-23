package com.hyundai.myexperience.ui.reservation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentReservationResultBinding
import com.hyundai.myexperience.databinding.FragmentReservationSessionHeadcountBinding

class ReservationResultFragment : Fragment() {
    private var _binding: FragmentReservationResultBinding? = null
    private val binding get() = _binding!!

    private val reservationViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationResultBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this
        binding.reservationViewModel = reservationViewModel
    }
}