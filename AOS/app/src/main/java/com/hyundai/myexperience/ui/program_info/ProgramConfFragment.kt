package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentProgramConfBinding
import com.hyundai.myexperience.ui.common.PagerImageAdapter

class ProgramConfFragment : Fragment() {
    private var _binding: FragmentProgramConfBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramConfBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf(R.drawable.program_category_iv_background, R.drawable.main_iv_background)
        val pagerImageAdapter = PagerImageAdapter(imageList)
        binding.vpProgramImage.adapter = pagerImageAdapter

        binding.btnProgramImagePrev.setOnClickListener {
            binding.vpProgramImage.currentItem = binding.vpProgramImage.currentItem - 1
        }

        binding.btnProgramImageNext.setOnClickListener {
            binding.vpProgramImage.currentItem = binding.vpProgramImage.currentItem + 1
        }

        TabLayoutMediator(binding.tlProgramImage, binding.vpProgramImage) { _, _ ->
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}