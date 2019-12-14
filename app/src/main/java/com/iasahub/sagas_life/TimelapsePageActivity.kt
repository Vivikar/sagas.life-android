package com.iasahub.sagas_life

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.PopupMenu
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
        timelapse_pic.setImageResource(getIntent().getStringExtra("TIMAGE").toInt())
        user_pic.setImageResource(getIntent().getStringExtra("TUSERPIC").toInt())

        go_back.setOnClickListener {
            onBackPressed()
        }

        timelapse_page_menu_btn.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener{item ->
                when (item.itemId){
                    R.id.timelapse_owner -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("str"))
                        startActivity(intent)
                        true
                    }
                    R.id.timelapse_co_author -> {
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
            startActivity(intent)
        }

        //tname.text = getIntent().getStringExtra("TNAME")
        //timelapse_pic.setImageResource(())
    }

}


