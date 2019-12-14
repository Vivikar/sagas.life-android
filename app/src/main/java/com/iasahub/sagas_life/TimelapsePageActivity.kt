package com.iasahub.sagas_life

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.iasahub.sagas_life.databinding.ActivityTimelapsePageBinding
import kotlinx.android.synthetic.main.activity_timelapse_page.*

class TimelapsePageActivity : AppCompatActivity() {
    lateinit var binding: ActivityTimelapsePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_timelapse_page)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_timelapse_page)

        tname.text = getIntent().getStringExtra("TNAME")
        tdescription.text = getIntent().getStringExtra("TDESCR")



        cross_fader.crossfade("https://bit.ua/wp-content/uploads/2019/05/kitsya.jpg")
        cross_fader.crossfade("http://m.spletnik.ru/img/2019/05/elizaveta/20190517-catpost.jpg")
        cross_fader.crossfade("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ55_OCuMKi7lwYRG_-qHBPoRcHmLrMTQglRdXX-sEpax_ofKoQ&s")
        //cross_fader.crossfade("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4vuuql_pX69t8jT7b0_fj0DbTGQY00GWMUoz9W4lo0GZ-djJZ9w&s")


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


