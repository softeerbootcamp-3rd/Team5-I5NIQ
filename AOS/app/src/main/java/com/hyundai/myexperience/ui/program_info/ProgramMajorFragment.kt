package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentProgramMajorBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProgramMajorFragment : Fragment() {
    private var _binding: FragmentProgramMajorBinding? = null
    private val binding get() = _binding!!

    private val programViewModel: ProgramViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramMajorBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this

        binding.programViewModel = programViewModel
    }
}