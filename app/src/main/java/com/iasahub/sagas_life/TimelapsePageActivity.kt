package com.iasahub.sagas_life

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.PopupMenu
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.iasahub.sagas_life.databinding.ActivityTimelapsePageBinding
import kotlinx.android.synthetic.main.activity_timelapse_page.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TimelapsePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityTimelapsePageBinding
    val camera_request_code = 0
    var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timelapse_page)

        like_num.text = getIntent().getIntExtra("TLIKES", 0).toString()
        share_num.text = getIntent().getIntExtra("TSHARES", 0).toString()
        comm_num.text = getIntent().getIntExtra("TCOMMS", 0).toString()

        tname.text = intent.getStringExtra("TNAME")
        tdescription.text = intent.getStringExtra("TDESCR")

        play_timelapse.setOnClickListener {
            cross_fader.crossfade(R.drawable.time_1)
            cross_fader.showPrevious()
            cross_fader.crossfade(R.drawable.time_2)

        }

        Glide.with(applicationContext)
            .load(intent.getStringExtra("TIMAGE").toInt())
            .into(timelapse_pic)

        Glide.with(applicationContext)
            .load(R.drawable.user_icon)
            .into(user_pic)

        go_back.setOnClickListener {
            val intent = Intent(this, TimelapsefeedActivity::class.java)
            startActivity(intent)
        }

        timelapse_page_menu_btn.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener{item ->
                when (item.itemId){
                    R.id.timelapse_owner -> {
                        //profile
                        true
                    }
                    R.id.timelapse_co_author -> {
                        //co-authors
                        true
                    }
                    R.id.report_about_slide -> {
                        //report
                        true
                    }
                    R.id.edit_timelaps ->{
                        true
                    }
                    R.id.delete_slide ->{
                        //get permition
                        val permition = false //gRPC
                        if(!permition){
                            //perition denied
                        }
                        else{
                            //gRPC delete slide
                        }
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.timelaps_page_menu)
            popupMenu.show()
        }

        open_comments_btn.setOnClickListener {
            val intent = Intent(this, CommentsPageActivity::class.java)
            intent.putExtra("TIMAGE", getIntent().getStringExtra("TIMAGE"))
            intent.putExtra("TNAME", getIntent().getStringExtra("TNAME"))
            intent.putExtra("TDESCR", getIntent().getStringExtra("TDESCR"))

            startActivity(intent)
        }

        TL_likes_page.setOnClickListener{
            //gRPC get liked or not
            //flag = ....
            if(!flag){
                like_num.text = (like_num.text.toString().toInt() + 1).toString()
                flag = true
                // gRPC add like
            }
            else{
                like_num.text = (like_num.text.toString().toInt() - 1).toString()
                flag = false
                //gRPC remove like
            }
        }
        shar_btn_page.setOnClickListener{
            //---------------
        }

        floating_action_button.setOnClickListener {

            val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (callCameraIntent.resolveActivity(packageManager) != null) {
                startActivityForResult(callCameraIntent, camera_request_code)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            camera_request_code -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    // gRPC send photo
                }
            }
            else -> {
                Toast.makeText(this,"Urecognize request code", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

