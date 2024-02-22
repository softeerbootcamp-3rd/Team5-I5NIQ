package com.hyundai.myexperience.ui.reservation_entrance

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
import com.hyundai.myexperience.databinding.FragmentQueueDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QueueDialogFragment : DialogFragment() {
    private var _binding: FragmentQueueDialogBinding? = null
    private val binding get() = _binding!!

    private val reservationEntranceViewModel: ReservationEntranceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQueueDialogBinding.inflate(inflater, container, false)
        initDataBinding()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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