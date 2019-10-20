package com.iasahub.morphy


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.facebook.AccessToken
import android.os.Handler


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)
        val SPLASH_TIME_OUT = 2000
        val homeIntent = Intent(this@SplashScreen, MainActivity::class.java)
        val already_logen_in = Intent(this@SplashScreen, AuthenticatedActivity::class.java)
        Handler().postDelayed({
            val accessToken = AccessToken.getCurrentAccessToken()
            if (accessToken != null) {
                //useLoginInformation(accessToken)
                startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            }
            else {
                startActivity(homeIntent)

            }

            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}