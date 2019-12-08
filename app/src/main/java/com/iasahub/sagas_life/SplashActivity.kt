package com.iasahub.sagas_life

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // убедитесь, что вызываете до super.onCreate()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        scheduleSplashScreen()

        /*
        val user = UserDb.getCurrentUser()
        routeToAppropriatePage(user)
        finish()
         */
    }


    private fun scheduleSplashScreen() {
        val splashScreenDuration = getSplashScreenDuration()
        Handler().postDelayed(
            {
                // После Splash Screen перенаправляем на нужную Activity
                startActivity(Intent(applicationContext, Timelapsefeed::class.java))
                finish()
            },
            splashScreenDuration
        )
    }

    private fun getSplashScreenDuration() = 1000L
}