package com.hyundai.myexperience.ui.signin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyundai.myexperience.R
import com.hyundai.myexperience.databinding.ActivitySignInBinding
import com.hyundai.myexperience.ui.signup.SignUpActivity
import com.hyundai.myexperience.utils.setStatusBarTransparent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDataBinding()

        val intent = Intent(this, SignUpActivity::class.java)
        binding.tvSignup.setOnClickListener { startActivity(intent) }

        this.setStatusBarTransparent()

        val toolbarLayout = binding.toolbarLayout
        toolbarLayout.toolBarTitle.text = ""
        setSupportActionBar(toolbarLayout.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        binding.lifecycleOwner = this

        binding.signInViewModel = signInViewModel
    }
}