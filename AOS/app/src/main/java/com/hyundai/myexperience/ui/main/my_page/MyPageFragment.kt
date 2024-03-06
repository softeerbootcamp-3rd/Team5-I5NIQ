package com.hyundai.myexperience.ui.main.my_page

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.hyundai.myexperience.JOINED_STATUS
import com.hyundai.myexperience.JOINED_TYPE_KEY
import com.hyundai.myexperience.PAID_PROGRAM
import com.hyundai.myexperience.R
import com.hyundai.myexperience.SCHEDULED_PROGRAM
import com.hyundai.myexperience.STATUS_UPCOMING
import com.hyundai.myexperience.databinding.FragmentMypageBinding
import com.hyundai.myexperience.ui.common.BasicAlertDialog
import com.hyundai.myexperience.ui.common.createTooltipOrientationTop
import com.hyundai.myexperience.ui.joined_program.JoinedProgramActivity
import com.hyundai.myexperience.ui.signin.SignInActivity
import com.hyundai.myexperience.utils.showToast
import com.skydoves.balloon.showAlignBottom
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : Fragment() {
    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private val myPageViewModel: MyPageViewModel by activityViewModels()

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

        binding.tvSignin.setOnClickListener {
            val intent = Intent(requireActivity(), SignInActivity::class.java)
            startActivity(intent)
        }

        binding.tvExpectedMore.setOnClickListener {
            val intent = Intent(requireActivity(), JoinedProgramActivity::class.java)
            intent.putExtra(JOINED_TYPE_KEY, SCHEDULED_PROGRAM)
            intent.putExtra(JOINED_STATUS, STATUS_UPCOMING)
            startActivity(intent)
        }

        binding.clJoined.setOnClickListener {
            val intent = Intent(requireActivity(), JoinedProgramActivity::class.java)
            intent.putExtra(JOINED_TYPE_KEY, PAID_PROGRAM)
            intent.putExtra(JOINED_STATUS, "")
            startActivity(intent)
        }

        val signOutDialog = getSignOutDialog()
        binding.tvSignout.setOnClickListener {
            signOutDialog.show(requireActivity().supportFragmentManager, "SignOutDialog")
        }

        binding.ivInfo.setOnClickListener {
            setToolTip()
        }
    }

    override fun onResume() {
        super.onResume()

        myPageViewModel.checkSignedInAndRequestMyPage()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initDataBinding() {
        binding.lifecycleOwner = this
        binding.myPageViewModel = myPageViewModel
    }

    private fun getSignOutDialog(): BasicAlertDialog {
        return BasicAlertDialog(
            resources.getString(R.string.mypage_dialog_signout),
            onOk = {
                myPageViewModel.requestSignOut()
                showToast(requireContext(), resources.getString(R.string.mypage_toast_signout))
            },
            okText = resources.getString(R.string.mypage_logout_btn),
            okTextColor = R.color.red
        )
    }

    fun setToolTip() {
        binding.vTooltipPoint.showAlignBottom(
            createTooltipOrientationTop(
                this.requireContext(),
                getString(R.string.mypage_level_info_tooltip)
            )
        )
    }
}