package com.iasahub.sagas_life

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
            cross_fader.crossfade("https://www.cbc.ca/news2/interactives/before-after/ukraine-independence-square/images/ind-square-before.jpg") //blue_kitsya
            cross_fader.showPrevious()
            cross_fader.crossfade("https://media.gettyimages.com/photos/independence-square-kiev-ukraine-picture-id480423837") //white_kitsya
            //cross_fader.showNext()
            //cross_fader.crossfade("https://www.interfax.ru/ftproot/textphotos/2019/05/17/700gc.jpg")  //rose_kitsya

        }




        timelapse_pic.setImageResource(getIntent().getStringExtra("TIMAGE").toInt())

//        Glide.with(this)
//            .load(getIntent().getStringExtra("TIMAGE").toInt())
//            .into(timelapse_pic)




        user_pic.setImageResource(getIntent().getStringExtra("TUSERPIC").toInt())

        go_back.setOnClickListener {
            onBackPressed()
        }

        //tname.text = getIntent().getStringExtra("TNAME")

    }

}

