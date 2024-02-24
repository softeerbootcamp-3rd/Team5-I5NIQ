package com.hyundai.myexperience.ui.common

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.FragmentAlertDialogBinding

class BasicAlertDialog(
    private val title: String,
    private val onCancel: () -> Unit = {},
    private val onOk: () -> Unit = {},
    private val cancelText: String = "취소",
    private val okText: String = "확인",
    private val cancelTextColor: Int = R.color.white,
    private val okTextColor: Int = R.color.white
) : DialogFragment() {
    private var _binding: FragmentAlertDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlertDialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = title
        binding.tvCancel.text = cancelText
        binding.tvOk.text = okText
        binding.tvCancel.setTextColor(ContextCompat.getColor(requireContext(), cancelTextColor))
        binding.tvOk.setTextColor(ContextCompat.getColor(requireContext(), okTextColor))

        binding.tvOk.setOnClickListener {
            onOk()
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            onCancel()
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