package com.hyundai.myexperience.ui.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.MESSAGE_EMPTY_FIELD
import com.hyundai.myexperience.MESSAGE_PASSWORD_WRONG
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivitySignUpBinding
import com.hyundai.myexperience.ui.common.BaseActivity
import com.hyundai.myexperience.ui.signin.SignInActivity
import com.hyundai.myexperience.utils.navigationHeight
import com.hyundai.myexperience.utils.setStatusBarTransparent
import com.hyundai.myexperience.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()
        initScreen()

        setIdValidationChecking()
        setPasswordValidationChecking()

        binding.btnSignup.setOnClickListener {
            onClickSignUpBtn()
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
    }

    private fun initScreen() {
        this.setStatusBarTransparent()
        binding.cl.setPadding(0, 0, 0, this.navigationHeight())

        setToolbar(
            binding.toolbarLayout.toolbar,
            binding.toolbarLayout.toolBarTitle,
            resources.getString(R.string.signup_btn)
        )
    }

    private fun setIdValidationChecking() {
        binding.etSignupId.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.isEmpty()) {
                    binding.tilSignupId.error = resources.getString(R.string.signup_id_double)
                } else {
                    binding.tilSignupId.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setPasswordValidationChecking() {
        binding.etSignupPasswordCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!isPasswordFormat(s.toString())) {
                    binding.tilSignupPasswordCheck.error =
                        resources.getString(R.string.signup_password_alert)
                } else {
                    binding.tilSignupPasswordCheck.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun isPasswordFormat(password: String): Boolean {
        return password.matches(".*[!@#$%^&*].*".toRegex())
    }

    private fun onClickSignUpBtn() {
        if (binding.etSignupPassword.text.toString() == binding.etSignupPasswordCheck.text.toString()) {
            signUpViewModel.requestSignUp(
                binding.etSignupId.text.toString(),
                binding.etSignupName.text.toString(),
                binding.etSignupPassword.text.toString()
            )
        } else if (binding.etSignupId.text.isNullOrEmpty() || binding.etSignupName.text.isNullOrEmpty() || binding.etSignupPassword.text.isNullOrEmpty()) {
            showToast(this, MESSAGE_EMPTY_FIELD)
        } else {
            showToast(this, MESSAGE_PASSWORD_WRONG)
        }

        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}