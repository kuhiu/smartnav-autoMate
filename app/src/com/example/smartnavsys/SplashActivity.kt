package com.example.smartnavsys

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import smartnavsys.R

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 5000 // 5 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed(

            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            , SPLASH_TIME_OUT)
    }
}
