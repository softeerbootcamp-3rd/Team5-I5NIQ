package com.hyundai.myexperience.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.PROGRAM_TYPE_KEY
import com.hyundai.myexperience.TYPE_PLEASURE
import com.hyundai.myexperience.databinding.FragmentProgramBinding
import com.hyundai.myexperience.ui.program_category.ProgramCategoryActivity

class ProgramFragment : Fragment() {
    private var _binding: FragmentProgramBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivExperienceBackground.setOnClickListener {
            val intent = Intent(requireActivity(), ProgramCategoryActivity::class.java)
            intent.putExtra(PROGRAM_TYPE_KEY, TYPE_PLEASURE)
            startActivity(intent)
        }

        binding.ivPleasureBackground.setOnClickListener {
            val intent = Intent(requireActivity(), ProgramCategoryActivity::class.java)
            intent.putExtra(PROGRAM_TYPE_KEY, TYPE_PLEASURE)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}