package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentProgramTrackBinding
import com.hyundai.myexperience.ui.common.adapter.PagerImageAdapter

class ProgramTrackFragment : Fragment() {
    private var _binding: FragmentProgramTrackBinding? = null
    private val binding get() = _binding!!

    private val programViewModel: ProgramViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramTrackBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImagePager()

        binding.btnMapImagePrev.setOnClickListener {
            binding.vpMapImage.currentItem = binding.vpMapImage.currentItem - 1
            programViewModel.setSelectedTrack(binding.vpMapImage.currentItem)
        }

        binding.btnMapImageNext.setOnClickListener {
            binding.vpMapImage.currentItem = binding.vpMapImage.currentItem + 1
            programViewModel.setSelectedTrack(binding.vpMapImage.currentItem)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this

        binding.programViewModel = programViewModel
    }

    private fun initImagePager() {
        val imageList = listOf(R.drawable.program_track_iv_1, R.drawable.program_track_iv_3)
        val mapImageAdapter = PagerImageAdapter(imageList)
        binding.vpMapImage.adapter = mapImageAdapter
    }
}