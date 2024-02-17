package com.hyundai.myexperience.ui.reservation.program_first

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.Level
import com.hyundai.myexperience.databinding.FragmentReservationProgramBinding
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener
import com.hyundai.myexperience.ui.reservation.adapter.LevelAdapter

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


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}