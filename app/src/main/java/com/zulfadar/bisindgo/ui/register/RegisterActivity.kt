package com.zulfadar.bisindgo.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.zulfadar.bisindgo.data.User
import com.zulfadar.bisindgo.ui.login.LoginActivity
import com.zulfadar.bisindgo.utils.RegisterValidation
import com.zulfadar.bisindgo.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

val TAG = "RegisterActivity"
@AndroidEntryPoint
class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()
    lateinit var userAuth : FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        binding.toLoginPage.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.apply {
            registerBtn.setOnClickListener {
                val user = User(
                    edtFirstname.text.toString().trim(),
                    edtLastname.text.toString().trim(),
                    edtEmailRegister.text.toString().trim(),
                    edtNotelp.text.toString().trim(),
                )
                val password = edtPasswordRegister.text.toString()
                val confirmPassword = edtKonfirpasswordRegister.text.toString()
                if (confirmPassword != password) {
                    binding.edtKonfirpasswordRegister.error = "Password tidak sama"
                    binding.edtKonfirpasswordRegister.requestFocus()
                    return@setOnClickListener
                }
                viewModel.createAccountWithEmailAndPassword(user, password)

            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when (it) {
                    is Resource.Loading -> {
                        binding.registerBtn.startAnimation()
                    }

                    is Resource.Success -> {
                        Log.d("test", it.data.toString())
                        binding.registerBtn.revertAnimation()
                        Toast.makeText(this@RegisterActivity, "Berhasil mendaftarkan akun!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                        startActivity(intent)
                    }

                    is Resource.Error -> {
                        Log.d(TAG, it.message.toString())
                        binding.registerBtn.revertAnimation()
                        Toast.makeText(this@RegisterActivity, "Terjadi kesalahan: ${it.message}", Toast.LENGTH_SHORT).show()
                    }

                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.validation.collect { validation ->
                if (validation.email is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.edtEmailRegister.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }

                if (validation.password is RegisterValidation.Failed) {
                    withContext(Dispatchers.Main) {
                        binding.edtPasswordRegister.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }
            }
        }
    }
}