package com.hyundai.myexperience.ui.joined_program

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.hyundai.myexperience.databinding.FragmentJoinedProgramDetailDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinedProgramDetailDialog() : DialogFragment() {
    private var _binding: FragmentJoinedProgramDetailDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel: JoinedProgramDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJoinedProgramDetailDialogBinding.inflate(inflater, container, false)
        initDataBinding()

        // dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 제목, 내용 설정
        //binding.customTvContent.text = content

        viewModel.setReservationId(0)
        viewModel.joinedProgramRequest()

        binding.tvPositive.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this
        binding.joinedProgramDetailModel = viewModel
    }
}