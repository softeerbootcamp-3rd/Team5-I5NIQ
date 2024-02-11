package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
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
        val programImageAdapter = PagerImageAdapter(imageList)
        binding.vpProgramImage.adapter = programImageAdapter

        val carImageAdapter = PagerImageAdapter(imageList)
        binding.vpCarImage.adapter = carImageAdapter

        binding.btnProgramImagePrev.setOnClickListener {
            binding.vpProgramImage.currentItem = binding.vpProgramImage.currentItem - 1
        }

        binding.btnProgramImageNext.setOnClickListener {
            binding.vpProgramImage.currentItem = binding.vpProgramImage.currentItem + 1
        }

        binding.btnCarImagePrev.setOnClickListener {
            binding.vpCarImage.currentItem = binding.vpCarImage.currentItem - 1
        }

        binding.btnCarImageNext.setOnClickListener {
            binding.vpCarImage.currentItem = binding.vpCarImage.currentItem + 1
        }

        TabLayoutMediator(binding.tlProgramImage, binding.vpProgramImage) { _, _ ->
        }.attach()

        initializeIndicators(imageList.size)

        binding.vpCarImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initializeIndicators(count: Int) {
        binding.llCarImageIndicator.removeAllViews()

        for (i in 0 until count) {
            val imageView = ImageView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT).also {
                    it.setMargins(8, 0, 8, 0)
                }
                setImageResource(R.drawable.icon_circle_unfilled)
            }
            binding.llCarImageIndicator.addView(imageView)
        }

        if (count > 0) {
            (binding.llCarImageIndicator.getChildAt(0) as ImageView).setImageResource(R.drawable.icon_circle_filled)
        }
    }

    fun updateIndicators(position: Int) {
        for (i in 0 until binding.llCarImageIndicator.childCount) {
            (binding.llCarImageIndicator.getChildAt(i) as ImageView).setImageResource(
                if (i == position) R.drawable.icon_circle_filled else R.drawable.icon_circle_unfilled
            )
        }
    }
}