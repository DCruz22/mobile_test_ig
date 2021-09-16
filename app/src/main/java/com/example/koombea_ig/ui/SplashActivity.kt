package com.example.koombea_ig.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.koombea_ig.R
import com.example.koombea_ig.worker.WorkerController

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        WorkerController.startDownloadWorker(applicationContext)

        Handler().postDelayed({
            continueTo(MainActivity::class.java)
        }, 1000)

    }

    private fun continueTo(destinationActivity: Class<*>){
        startActivity(Intent(this@SplashActivity, destinationActivity))
        finish()
    }
}