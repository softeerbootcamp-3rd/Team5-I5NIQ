package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentProgramConfBinding
import com.hyundai.myexperience.ui.common.PagerImageAdapter

class ProgramConfFragment : Fragment() {
    private var _binding: FragmentProgramConfBinding? = null
    private val binding get() = _binding!!

    private val programViewModel: ProgramViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramConfBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImagePager()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this

        binding.programViewModel = programViewModel
    }

    private fun initImagePager() {
        val confImageList = listOf(R.drawable.program_conf_iv_image_1, R.drawable.program_conf_iv_image_2)
        val programImageAdapter = PagerImageAdapter(confImageList)
        binding.vpProgramImage.adapter = programImageAdapter

        val carImageList = listOf(R.drawable.program_conf_iv_avante_n, R.drawable.program_conf_iv_avante_n_line)
        val carImageAdapter = PagerImageAdapter(carImageList)
        binding.vpCarImage.adapter = carImageAdapter

        TabLayoutMediator(binding.tlProgramImage, binding.vpProgramImage) { _, _ ->
        }.attach()

        initIndicator(carImageList.size)

        binding.vpCarImage.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateIndicators(position)
            }
        })
    }

    private fun initIndicator(count: Int) {
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