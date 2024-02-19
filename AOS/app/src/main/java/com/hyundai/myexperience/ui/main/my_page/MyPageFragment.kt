package com.hyundai.myexperience.ui.main.my_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hyundai.myexperience.JOINED_TYPE_KEY
import com.hyundai.myexperience.PAID_PROGRAM
import com.hyundai.myexperience.SCHEDULED_PROGRAM
import com.hyundai.myexperience.databinding.FragmentMypageBinding
import com.hyundai.myexperience.ui.joined_program.JoinedProgramActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private val myPageViewModel: MyPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mypageTvExpectedMore.setOnClickListener {
            val intent = Intent(requireActivity(), JoinedProgramActivity::class.java)
            intent.putExtra(JOINED_TYPE_KEY, SCHEDULED_PROGRAM)
            startActivity(intent)
        }

        binding.mypageClJoined.setOnClickListener {
            val intent = Intent(requireActivity(), JoinedProgramActivity::class.java)
            intent.putExtra(JOINED_TYPE_KEY, PAID_PROGRAM)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this
        binding.myPageViewModel = myPageViewModel
    }
}