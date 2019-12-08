package com.iasahub.sagas_life

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.iasahub.sagas_life.databinding.ActivityTimelapsefeedBinding
import kotlinx.android.synthetic.main.activity_timelapsefeed.*

class Timelapsefeed : AppCompatActivity() {

    lateinit var binding: ActivityTimelapsefeedBinding
    lateinit var timelapse_feed_list: ArrayList<Timelapses>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_timelapsefeed)
        timelapse_feed_list = ArrayList()

        addTimelapse()
        TimeRecycler.layoutManager = LinearLayoutManager(this)
        TimeRecycler.addItemDecoration((DividerItemDecoration(this, 1)))
        TimeRecycler.adapter = TimelapseAdapter(timelapse_feed_list)

        //setContentView(R.layout.activity_timelapsefeed)
    }

    fun addTimelapse(){
        timelapse_feed_list.add(Timelapses("Somewhere in the Universe", "#universe #sky #nice", R.drawable.user_icon, R.drawable.timelapse_1))
        timelapse_feed_list.add(Timelapses("Somewhere in the Universe", "#universe #sky #nice", R.drawable.user_icon, R.drawable.timelapse_2))
        timelapse_feed_list.add(Timelapses("Somewhere in the Universe", "#universe #sky #nice", R.drawable.user_icon, R.drawable.timelapse_3))
        timelapse_feed_list.add(Timelapses("Somewhere in Alps", "#apls #lake #landscape", R.drawable.user_icon, R.drawable.timelapse_4))
        timelapse_feed_list.add(Timelapses("Somewhere in Alps", "#apls #lake #landscape", R.drawable.user_icon, R.drawable.timelapse_5))
        timelapse_feed_list.add(Timelapses("Somewhere in Alps", "#apls #lake #landscape", R.drawable.user_icon, R.drawable.timelapse_6))
        timelapse_feed_list.add(Timelapses("Somewhere in Alps", "#apls #lake #landscape", R.drawable.user_icon, R.drawable.timelapse_7))
    }
}
