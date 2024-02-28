package com.hyundai.myexperience.ui.program_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.data.entity.program.Comment
import com.hyundai.myexperience.databinding.FragmentProgramCommentBinding
import com.hyundai.myexperience.ui.program_info.adapter.CommentAdapter

class ProgramCommentFragment : Fragment() {
    private var _binding: FragmentProgramCommentBinding? = null
    private val binding get() = _binding!!

    private val programViewModel: ProgramViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgramCommentBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCommentRecyclerView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this

    }

    private fun initCommentRecyclerView() {
        val adapter = CommentAdapter(programViewModel.comments.value!!)

        binding.rvComments.adapter = adapter
        programViewModel.comments.observe(requireActivity()) {
            adapter.setData(it)
        }

        binding.rvComments.layoutManager = LinearLayoutManager(requireContext())
    }
}