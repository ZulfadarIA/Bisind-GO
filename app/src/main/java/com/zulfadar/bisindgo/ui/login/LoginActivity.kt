package com.zulfadar.bisindgo.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.zulfadar.bisindgo.MainActivity
import com.zulfadar.bisindgo.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var userAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        userAuth = FirebaseAuth.getInstance()

        supportActionBar?.hide()

        binding.toregisterPage.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val password = binding.edtPasswordLogin.text.toString()

            //validasi email
            if (email.isEmpty()) {
                binding.edtEmailLogin.error = "Email tidak boleh kosong!"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            //validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmailLogin.error = "Email tidak valid"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            //validasi password
            if (password.isEmpty()) {
                binding.edtPasswordLogin.error = "Password harus diisi!"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            //validasi panjang password
            if (password.length < 8) {
                binding.edtPasswordLogin.error = "Password minmal 8 karakter!"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email, password)
        }
    }

    private fun LoginFirebase(email: String, password: String) {
        userAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
            if (it.isSuccessful) {
                Toast.makeText(this, "Berhasil login, Selamat Datang $email", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }
}