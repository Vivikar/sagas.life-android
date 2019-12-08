package com.iasahub.sagas_life

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.iasahub.sagas_life.databinding.ActivityTimelapsePageBinding
import com.iasahub.sagas_life.databinding.ActivityTimelapsefeedBinding
import kotlinx.android.synthetic.main.activity_timelapse_page.*

class TimelapsePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityTimelapsePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_timelapse_page)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timelapse_page)

        tname.text = getIntent().getStringExtra("TNAME")
        tdescription.text = getIntent().getStringExtra("TDESCR")
        timelapse_pic.setImageResource(getIntent().getStringExtra("TIMAGE").toInt())
        user_pic.setImageResource(getIntent().getStringExtra("TUSERPIC").toInt())

        go_back.setOnClickListener {
            onBackPressed()
        }

        //tname.text = getIntent().getStringExtra("TNAME")
        //timelapse_pic.setImageResource(())
    }

}


