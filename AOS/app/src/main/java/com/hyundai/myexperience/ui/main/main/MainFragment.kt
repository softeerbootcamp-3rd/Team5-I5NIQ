package com.hyundai.myexperience.ui.main.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentMainBinding
import com.hyundai.myexperience.ui.reservation_entrance.ReservationEntranceActivity

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
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

        setIntroVideo()

        binding.btnReservation.setOnClickListener {
            val intent = Intent(requireActivity(), ReservationEntranceActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.vvIntroVideo.start()
    }

    override fun onPause() {
        super.onPause()
        if (binding.vvIntroVideo.isPlaying) {
            binding.vvIntroVideo.pause()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding(inflater: LayoutInflater, container: ViewGroup?) {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
    }

    private fun setIntroVideo() {
        binding.vvIntroVideo.let {
            it.setVideoURI(Uri.parse("android.resource://${requireContext().packageName}/${R.raw.intro_video}"))
            it.setOnCompletionListener { player ->
                player.start()
            }
        }
    }
}