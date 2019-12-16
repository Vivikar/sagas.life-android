package com.iasahub.sagas_life

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iasahub.sagas_life.databinding.ActivityCommentsPageBinding
import kotlinx.android.synthetic.main.activity_comments_page.*
import kotlinx.android.synthetic.main.activity_comments_page.tdescription
import kotlinx.android.synthetic.main.activity_comments_page.tname
import kotlinx.android.synthetic.main.activity_timelapse_page.*
import kotlinx.android.synthetic.main.activity_timelapsefeed.*
import kotlinx.android.synthetic.main.activity_timelapsefeed.massages_slider
import kotlinx.android.synthetic.main.activity_timelapsefeed.toolbar

class CommentsPageActivity: AppCompatActivity(), OnSettingsClickListener {

    lateinit var binding: ActivityCommentsPageBinding
    //lateinit var massage_feed_list: ArrayList<Massages>

    override fun onCreate(savedInstanceState: Bundle?) {
        // убедитесь, что вызываете до super.onCreate()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_comments_page)
        if(massageclass.massage_feed_list.size == 0){
            //massageclass.massage_feed_list = ArrayList()
            addComments()
        }

        timelapse.setImageResource(getIntent().getStringExtra("TIMAGE").toInt())
        tname.text = getIntent().getStringExtra("TNAME")
        tdescription.text = getIntent().getStringExtra("TDESCR")

        massages_slider.layoutManager = LinearLayoutManager(this) //as RecyclerView.LayoutManager?
        massages_slider.addItemDecoration((DividerItemDecoration(this, 1)))
        massages_slider.adapter = CommentsAdapter(massageclass.massage_feed_list, this)

        setSupportActionBar(toolbar)

        close_comments_btn.setOnClickListener{
            onBackPressed()
        }
        setNumOfComm()
        SendMassagesClicking()
    }

    fun setNumOfComm(){
        //gRPC get comments num
        val num = massageclass.massage_feed_list.size
        comments_num.text = num.toString() + " comments"
    }

    fun SendMassagesClicking(){
        send_msg_btn.setOnClickListener{
            val text_res = massage_text_inputer.text.toString()
            //gRPC getUser getUserIcon
            massageclass.massage_feed_list.add(Massages("User", text_res, R.drawable.user_icon, "1m", false))
            //gRPC send new massage
            massage_text_inputer.text = null
            setNumOfComm()
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun addComments(){
        massageclass.massage_feed_list.add(Massages("Donald", "Cool picture", R.drawable.user_icon, "1d", false))
        massageclass.massage_feed_list.add(Massages("John", "Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice. Nice.", R.drawable.user_icon, "18h", false))
        massageclass.massage_feed_list.add(Massages("Ralph", "Like it", R.drawable.user_icon, "10h",false))
        massageclass.massage_feed_list.add(Massages("Sarah", "I`ve been here some years ago. It was cool.", R.drawable.user_icon, "1h", true))
        massageclass.massage_feed_list.add(Massages("Ostin", "Lucky man", R.drawable.user_icon, "30m",false))
    }

    override fun SettingsClicking(item: Massages, position: Int, btn: ImageButton) {
        val popupMenu = PopupMenu(this, btn)
        popupMenu.setOnMenuItemClickListener{item ->
            when (item.itemId){
                R.id.report_btn -> {
                    //report
                    true
                }
                R.id.hide_btn -> {
                    //hide???
                    true
                }
                else -> false
            }
        }
        popupMenu.inflate(R.menu.comment_menu)
        popupMenu.show()
    }
}