package com.hyundai.myexperience.ui.reservation

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.hyundai.myexperience.databinding.FragmentReservationDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReservationDialogFragment : DialogFragment() {
    private var _binding: FragmentReservationDialogBinding? = null
    private val binding get() = _binding!!

    private val reservationEntranceViewModel: ReservationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReservationDialogBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvOk.setOnClickListener {
            reservationEntranceViewModel.requestReservation()
        }

        binding.tvCancel.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onStart() {
        super.onStart()
        setDialog()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this
        binding.reservationViewModel = reservationEntranceViewModel
    }

    private fun setDialog() {
        dialog?.window?.let { window ->
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.WRAP_CONTENT
            window.setLayout(width, height)

            window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            window.setGravity(Gravity.CENTER)
        }
    }
}