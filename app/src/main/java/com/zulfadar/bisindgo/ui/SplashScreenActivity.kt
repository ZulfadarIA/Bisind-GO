package com.zulfadar.bisindgo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.dicoding.projecttapenerjemahbahasaisyaratindonesia.R
import com.zulfadar.bisindgo.MainActivity
import com.zulfadar.bisindgo.ui.login.LoginActivity
import com.zulfadar.bisindgo.ui.register.RegisterActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        val backgrounding : ImageView = findViewById(R.id.iv_logo)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.slide)
        backgrounding.startAnimation(slideAnimation)

        Handler().postDelayed({
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }, 3000)
    }
}