package com.iasahub.morphy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.facebook.login.LoginManager
import com.facebook.share.widget.ShareDialog

import android.widget.TextView
import com.facebook.*


import org.json.JSONException

import com.facebook.GraphRequest
import com.facebook.AccessToken
import kotlinx.android.synthetic.main.activity_authenticated.*

import android.widget.ImageView

import com.bumptech.glide.Glide


class AuthenticatedActivity : AppCompatActivity() {

    private val shareDialog: ShareDialog? = null
    private var name: String? = null
    private var surname: String? = null
    private var imageUrl: String? = null
    //private val TAG = "AuthenticatedActivity"

    private var callbackManager: CallbackManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authenticated)

        var btnLogout = findViewById<Button>(R.id.btnLogout)


        val accessToken = AccessToken.getCurrentAccessToken()
        var lg_toekn = findViewById<TextView>(R.id.lg_toekn)
        var lg_email = findViewById<TextView>(R.id.lg_email)
        var lg_image = findViewById<ImageView>(R.id.fb_image)
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
        /**
         * Creating the GraphRequest to fetch user details
         * 1st Param - AccessToken
         * 2nd Param - Callback (which will be invoked once the request is successful)
         */
        val request = GraphRequest.newMeRequest(accessToken
        ) { `object`, response ->
            //OnCompleted is invoked once the GraphRequest is successful
            try {
                val name = `object`.getString("name")
                val email = `object`.getString("email")
                val image_url = `object`.getJSONObject("picture").getJSONObject("data").getString("url")
                //displayName.setText(name)


                var userId = AccessToken.getCurrentAccessToken().userId
                Glide.with(this)
                    .load("https://graph.facebook.com/" + userId +  "/picture?type=large")
                    .into(fb_image);


                lg_email.setText(email)
                lg_toekn.setText(accessToken.token)
                //fb_image.setImageURI()

            } catch (e:JSONException) {
                e.printStackTrace()
            }
        }
        // We set parameters to the GraphRequest using a Bundle.
        val parameters = Bundle()
        parameters.putString("fields", "id,name,email,picture.width(200)")
        request.parameters = parameters
        // Initiate the GraphRequest
        request.executeAsync()
    }
}
