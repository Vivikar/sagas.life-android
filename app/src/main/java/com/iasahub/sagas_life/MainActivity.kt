package com.iasahub.sagas_life

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.facebook.*


import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.iasahub.sagas_life.R
import kotlinx.android.synthetic.main.activity_authenticated.*
import org.json.JSONException
import java.util.*

class MainActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null

    public override fun onStart() {
        super.onStart()
        val accessToken = AccessToken.getCurrentAccessToken()
        if (accessToken != null) {
            //useLoginInformation(accessToken)
            startActivity(Intent(applicationContext, AuthenticatedActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var loginButton = findViewById<Button>(R.id.login_button)




        loginButton.setOnClickListener(View.OnClickListener {
            // Login
            callbackManager = CallbackManager.Factory.create()
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "email"))
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("MainActivity", "Facebook token: " + loginResult.accessToken.token)
                        startActivity(Intent(applicationContext, AuthenticatedActivity::class.java))
                    }

                    override fun onCancel() {
                        Log.d("MainActivity", "Facebook onCancel.")

                    }

                    override fun onError(error: FacebookException) {
                        Log.d("MainActivity", "Facebook onError.")

                    }
                })
        })
    }


    private fun useLoginInformation(accessToken: AccessToken) {
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
                val image = `object`.getJSONObject("picture").getJSONObject("data").getString("url")


                //displayName.setText(name)


                lg_email.setText(email)
                lg_toekn.setText(accessToken.token)


            } catch (e: JSONException) {
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


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}

