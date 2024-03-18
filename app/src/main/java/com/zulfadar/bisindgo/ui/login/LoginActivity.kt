package com.zulfadar.bisindgo.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.zulfadar.bisindgo.MainActivity
import com.zulfadar.bisindgo.ui.dialog.setupBottomSheetDialog
import com.zulfadar.bisindgo.ui.register.RegisterActivity
import com.zulfadar.bisindgo.ui.register.TAG
import com.zulfadar.bisindgo.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()
    lateinit var userAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        binding.apply {
            loginBtn.setOnClickListener {
                val email = edtEmailLogin.text.toString().trim()
                val password = edtPasswordLogin.text.toString()
                viewModel.login(email, password)
            }
        }

        binding.toregisterPage.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.forgotPassword.setOnClickListener {
            setupBottomSheetDialog { email ->
                viewModel.resetPassword(email)
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.resetPassword.collect {
                when(it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        Snackbar.make(binding.root, "Tautan reset passwird sudah dikirim ke email Anda", Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Error -> {
                        Snackbar.make(binding.root, "Error: ${it.message}", Snackbar.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.login.collect {
                when(it) {
                    is Resource.Loading -> {
                        binding.loginBtn.startAnimation()
                    }
                    is Resource.Success -> {
                        binding.loginBtn.revertAnimation()
                        // Intent ke MainActivity dengan fragment
                        Intent(this@LoginActivity, MainActivity::class.java).also { intent ->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    is Resource.Error -> {
                        Log.d(TAG, it.message.toString())
                        Toast.makeText(this@LoginActivity, "Terjadi kesalahan: ${it.message}", Toast.LENGTH_LONG).show()
                        binding.loginBtn.revertAnimation()
                    }
                    else -> Unit
                }
            }
        }
    }
}