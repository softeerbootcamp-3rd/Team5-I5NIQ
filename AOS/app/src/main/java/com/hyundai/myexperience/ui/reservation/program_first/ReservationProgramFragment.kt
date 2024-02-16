package com.hyundai.myexperience.ui.reservation.program_first

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hyundai.myexperience.R
import com.hyundai.myexperience.data.entity.Level
import com.hyundai.myexperience.databinding.FragmentReservationProgramBinding
import com.hyundai.myexperience.ui.reservation.listener.LevelClickListener
import com.hyundai.myexperience.ui.reservation.adapter.LevelAdapter

class ReservationProgramFragment : Fragment() {
    private var _binding: FragmentReservationProgramBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCardViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initCardViews() {
        val clicked = mutableListOf(false, false, false, false, false, false, false)

        val white = ContextCompat.getColor(requireContext(), R.color.white)
        val gray2 = ContextCompat.getColor(requireContext(), R.color.gray2)

        val levelList = listOf(
            Level("Level 1", "예약가능"),
            Level("Level 2", "예약가능"),
            Level("Level 3", "예약가능"),
            Level("Off Road", "예약가능")
        )

        binding.rvHyundai.adapter = LevelAdapter(levelList, object : LevelClickListener {
            override fun onLevelClick(level: String) {
                binding.tvHyundaiSelected.text = level
                binding.cvHyundai.callOnClick()
            }
        })
        binding.rvHyundai.layoutManager = LinearLayoutManager(requireContext())

        binding.rvKia.adapter = LevelAdapter(levelList, object : LevelClickListener {
            override fun onLevelClick(level: String) {
                binding.tvKiaSelected.text = level
                binding.cvKia.callOnClick()
            }
        })
        binding.rvKia.layoutManager = LinearLayoutManager(requireContext())

        binding.rvGenesis.adapter = LevelAdapter(levelList, object : LevelClickListener {
            override fun onLevelClick(level: String) {
                binding.tvGenesisSelected.text = level
                binding.cvGenesis.callOnClick()
            }
        })
        binding.rvGenesis.layoutManager = LinearLayoutManager(requireContext())

        binding.rvHmgExperience.adapter = LevelAdapter(levelList, object : LevelClickListener {
            override fun onLevelClick(level: String) {
                binding.tvHmgExperienceSelected.text = level
                binding.cvHmgExperience.callOnClick()
            }
        })
        binding.rvHmgExperience.layoutManager = LinearLayoutManager(requireContext())

        binding.cvHyundai.setOnClickListener {
            if (clicked[0]) {
                binding.cvHyundai.strokeColor = gray2
                binding.tvHyundai.setTextColor(gray2)
                binding.tvHyundaiSelected.setTextColor(gray2)
                binding.ivArrowHyundai.imageTintList = ColorStateList.valueOf(gray2)
                binding.ivArrowHyundai.rotation = 0f
                binding.rvHyundai.visibility = View.GONE
            } else {
                binding.cvHyundai.strokeColor = white
                binding.tvHyundai.setTextColor(white)
                binding.tvHyundaiSelected.setTextColor(white)
                binding.ivArrowHyundai.imageTintList = ColorStateList.valueOf(white)
                binding.ivArrowHyundai.rotation = 180f
                binding.rvHyundai.visibility = View.VISIBLE
            }

            clicked[0] = !clicked[0]
        }

        binding.cvKia.setOnClickListener {
            if (clicked[1]) {
                binding.cvKia.strokeColor = gray2
                binding.tvKia.setTextColor(gray2)
                binding.tvKiaSelected.setTextColor(gray2)
                binding.ivArrowKia.imageTintList = ColorStateList.valueOf(gray2)
                binding.ivArrowKia.rotation = 0f
                binding.rvKia.visibility = View.GONE
            } else {
                binding.cvKia.strokeColor = white
                binding.tvKia.setTextColor(white)
                binding.tvKiaSelected.setTextColor(white)
                binding.ivArrowKia.imageTintList = ColorStateList.valueOf(white)
                binding.ivArrowKia.rotation = 180f
                binding.rvKia.visibility = View.VISIBLE
            }

            clicked[1] = !clicked[1]
        }

        binding.cvGenesis.setOnClickListener {
            if (clicked[2]) {
                binding.cvGenesis.strokeColor = gray2
                binding.tvGenesis.setTextColor(gray2)
                binding.tvGenesisSelected.setTextColor(gray2)
                binding.ivArrowGenesis.imageTintList = ColorStateList.valueOf(gray2)
                binding.ivArrowGenesis.rotation = 0f
                binding.rvGenesis.visibility = View.GONE
            } else {
                binding.cvGenesis.strokeColor = white
                binding.tvGenesis.setTextColor(white)
                binding.tvGenesisSelected.setTextColor(white)
                binding.ivArrowGenesis.imageTintList = ColorStateList.valueOf(white)
                binding.ivArrowGenesis.rotation = 180f
                binding.rvGenesis.visibility = View.VISIBLE
            }

            clicked[2] = !clicked[2]
        }

        binding.cvHmgExperience.setOnClickListener {
            if (clicked[3]) {
                binding.cvHmgExperience.strokeColor = gray2
                binding.tvHmgExperience.setTextColor(gray2)
                binding.tvHmgExperienceSelected.setTextColor(gray2)
                binding.ivArrowHmgExperience.imageTintList = ColorStateList.valueOf(gray2)
                binding.ivArrowHmgExperience.rotation = 0f
                binding.rvHmgExperience.visibility = View.GONE
            } else {
                binding.cvHmgExperience.strokeColor = white
                binding.tvHmgExperience.setTextColor(white)
                binding.tvHmgExperienceSelected.setTextColor(white)
                binding.ivArrowHmgExperience.imageTintList = ColorStateList.valueOf(white)
                binding.ivArrowHmgExperience.rotation = 180f
                binding.rvHmgExperience.visibility = View.VISIBLE
            }

            clicked[3] = !clicked[3]
        }
    }
}