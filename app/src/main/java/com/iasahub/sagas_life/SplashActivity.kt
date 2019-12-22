package com.iasahub.sagas_life

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.util.ArrayList

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        timelapseclass.timelapse_feed_list = ArrayList()
        massageclass.massage_feed_list = ArrayList()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        scheduleSplashScreen()

    }

    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
            {
                startActivity(Intent(applicationContext, TimelapsefeedActivity::class.java))
                finish()
            },
            splashScreenDuration
        )
    }

    private fun getSplashScreenDuration() = 1000L
}