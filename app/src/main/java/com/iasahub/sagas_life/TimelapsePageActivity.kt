package com.iasahub.sagas_life

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.iasahub.sagas_life.databinding.ActivityTimelapsePageBinding
import kotlinx.android.synthetic.main.activity_timelapse_page.*

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TimelapsePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityTimelapsePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_timelapse_page)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timelapse_page)

        tname.text = getIntent().getStringExtra("TNAME")
        tdescription.text = getIntent().getStringExtra("TDESCR")

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
            onBackPressed()
        }

    }

}

