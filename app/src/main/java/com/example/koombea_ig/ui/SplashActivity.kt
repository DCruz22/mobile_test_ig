package com.example.koombea_ig.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.koombea_ig.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            continueTo(MainActivity::class.java)
        }, 500)

    }

    private fun continueTo(destinationActivity: Class<*>){
        startActivity(Intent(this@SplashActivity, destinationActivity))
        finish()
    }
}