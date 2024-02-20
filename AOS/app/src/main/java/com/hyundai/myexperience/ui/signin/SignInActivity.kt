package com.hyundai.myexperience.ui.signin

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.MESSAGE_EMPTY_FIELD
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivitySignInBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.main.MainActivity
import com.hyundai.myexperience.ui.signup.SignUpActivity
import com.hyundai.myexperience.utils.setStatusBarTransparent
import com.hyundai.myexperience.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : BaseActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        binding.btnSignin.setOnClickListener {
            onClickSignInBtn()
        }

        binding.tvSignup.setOnClickListener {
            onClickSignUpBtn()
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.lifecycleOwner = this

        binding.signInViewModel = signInViewModel
    }

    private fun initScreen() {
        this.setStatusBarTransparent()

        setToolbar(
            binding.toolbarLayout.toolbar,
            binding.toolbarLayout.toolBarTitle,
            ""
        )
    }

    private fun onClickSignInBtn() {
        if (binding.etSigninId.text.isNullOrEmpty() || binding.etSigninPassword.text.isNullOrEmpty()) {
            showToast(this, MESSAGE_EMPTY_FIELD)
        } else {
            signInViewModel.requestSignIn(
                binding.etSigninId.text.toString(),
                binding.etSigninPassword.text.toString()
            )

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun onClickSignUpBtn() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }
}