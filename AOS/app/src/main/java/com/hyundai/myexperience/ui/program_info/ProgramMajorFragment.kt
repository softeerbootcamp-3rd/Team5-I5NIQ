package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentProgramMajorBinding

class ProgramMajorFragment : Fragment() {
    private var _binding: FragmentProgramMajorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramMajorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}