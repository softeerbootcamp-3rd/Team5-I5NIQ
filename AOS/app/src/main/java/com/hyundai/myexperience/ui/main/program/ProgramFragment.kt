package com.hyundai.myexperience.ui.main.program

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.PROGRAM_TYPE_KEY
import com.hyundai.myexperience.TYPE_EXPERIENCE
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
        initDataBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivExperienceBackground.setOnClickListener {
            moveToProgramActivity(TYPE_EXPERIENCE)
        }

        binding.ivPleasureBackground.setOnClickListener {
            moveToProgramActivity(TYPE_PLEASURE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentProgramBinding.inflate(inflater, container, false)
    }

    private fun moveToProgramActivity(type: String) {
        val intent = Intent(requireActivity(), ProgramCategoryActivity::class.java)
        intent.putExtra(PROGRAM_TYPE_KEY, type)
        startActivity(intent)
    }
}