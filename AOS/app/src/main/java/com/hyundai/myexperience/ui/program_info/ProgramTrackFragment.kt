package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentProgramTrackBinding
import com.hyundai.myexperience.ui.common.PagerImageAdapter

class ProgramTrackFragment : Fragment() {
    private var _binding: FragmentProgramTrackBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramTrackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageList = listOf(R.drawable.program_category_iv_background, R.drawable.main_iv_background)
        val mapImageAdapter = PagerImageAdapter(imageList)
        binding.vpMapImage.adapter = mapImageAdapter

        binding.btnMapImagePrev.setOnClickListener {
            binding.vpMapImage.currentItem = binding.vpMapImage.currentItem - 1
        }

        binding.btnMapImageNext.setOnClickListener {
            binding.vpMapImage.currentItem = binding.vpMapImage.currentItem + 1
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}