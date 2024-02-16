package com.hyundai.myexperience.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentMypageBinding
import com.hyundai.myexperience.ui.joined_program.JoinedProgramActivity

class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mypageTvExpectedMore.setOnClickListener {
            val intent = Intent(requireActivity(), JoinedProgramActivity::class.java)
            intent.putExtra("title", "참여 예정 프로그램")
            startActivity(intent)
        }

        binding.mypageClJoined.setOnClickListener {
            val intent = Intent(requireActivity(), JoinedProgramActivity::class.java)
            intent.putExtra("title", "결제 완료 프로그램")
            startActivity(intent)
        }
    }


}