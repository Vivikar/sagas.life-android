package com.iasahub.sagas_life

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.facebook.login.LoginManager

import com.facebook.*


import org.json.JSONException

import com.facebook.GraphRequest
import com.facebook.AccessToken
import kotlinx.android.synthetic.main.activity_authenticated.*


import com.bumptech.glide.Glide

class AuthenticatedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticated)

        val btnLogout = findViewById<Button>(R.id.btnLogout)


        val accessToken = AccessToken.getCurrentAccessToken()
        if (!accessToken.isExpired){
            useLoginInformation(accessToken)

        }

        btnLogout.setOnClickListener(View.OnClickListener {
            // Logout
            if (AccessToken.getCurrentAccessToken() != null) {
                GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, GraphRequest.Callback {
                    AccessToken.setCurrentAccessToken(null)
                    LoginManager.getInstance().logOut()
                    finish()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }).executeAsync()
            }
        })
    }

    private fun useLoginInformation(accessToken:AccessToken) {

        val request = GraphRequest.newMeRequest(accessToken
        ) { `object`, _ ->
            try {
                val email = `object`.getString("email")
                val userId = AccessToken.getCurrentAccessToken().userId
                Glide.with(this)
                    .load("https://graph.facebook.com/$userId/picture?type=large")
                    .into(fb_image)
                lg_email.text = email
                lg_toekn.text = accessToken.token
                Log.println(Log.INFO, "TOKEN_FB", accessToken.token)
                //fb_image.setImageURI()

            } catch (e:JSONException) {
                e.printStackTrace()
            }
        }
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        request.executeAsync()
    }
}
