package com.iasahub.sagas_life

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_timelapsefeed.*

class CommentsPageActivity: AppCompatActivity() {

    //lateinit var massage_feed_list: ArrayList<Massages>

    override fun onCreate(savedInstanceState: Bundle?) {
        // убедитесь, что вызываете до super.onCreate()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments_page)

        //addComments()
        //massages_slider.layoutManager = LinearLayoutManager(this)
        //massages_slider.addItemDecoration((DividerItemDecoration(this, 1)))
        //massages_slider.adapter = CommentsAdapter(massage_feed_list)

        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    /*fun addComments(){
        massage_feed_list.add(Massages("Donald", "Cool picture", R.drawable.user_icon, "1d", false))
        massage_feed_list.add(Massages("John", "Nice", R.drawable.user_icon, "18h", false))
        massage_feed_list.add(Massages("Ralph", "Like it", R.drawable.user_icon, "10h",false))
        massage_feed_list.add(Massages("Sarah", "I`ve been here some years ago. It was cool", R.drawable.user_icon, "1h", true))
        massage_feed_list.add(Massages("Ostin", "Lucky man", R.drawable.user_icon, "30m",false))
    }*/

}