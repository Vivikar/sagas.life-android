package com.iasahub.morphy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.login.LoginManager
import com.facebook.share.widget.ShareDialog





class AuthenticatedActivity : AppCompatActivity() {

    private val shareDialog: ShareDialog? = null
    private var name: String? = null
    private var surname: String? = null
    private var imageUrl: String? = null
    //private val TAG = "AuthenticatedActivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticated)

        var btnLogout = findViewById<Button>(R.id.btnLogout)

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
}
