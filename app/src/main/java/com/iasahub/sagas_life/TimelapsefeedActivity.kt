package com.iasahub.sagas_life

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iasahub.sagas_life.databinding.ActivityTimelapsefeedBinding
import kotlinx.android.synthetic.main.activity_timelapsefeed.*

class TimelapsefeedActivity : AppCompatActivity(), OnTimelapseItemClickListener {

    lateinit var binding: ActivityTimelapsefeedBinding
    //lateinit var timelapse_feed_list: ArrayList<Timelapses>
    val camera_request_code = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_timelapsefeed)
        if(timelapseclass.timelapse_feed_list.size == 0){
            //timelapseclass.timelapse_feed_list = ArrayList()
            addTimelapse()
        }

        massages_slider.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        massages_slider.addItemDecoration((DividerItemDecoration(this, 1)))
        massages_slider.adapter = TimelapseAdapter(timelapseclass.timelapse_feed_list, this)

        setSupportActionBar(toolbar)

        bottom_navigation.setOnNavigationItemSelectedListener(){
            when (it.itemId){
                R.id.add_pic -> {
                val callCameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (callCameraIntent.resolveActivity(packageManager) != null){
                    startActivityForResult(callCameraIntent, camera_request_code)
                }
                true
            }
                else -> false
            }
        }

        //setContentView(R.layout.activity_timelapsefeed)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode) {
            camera_request_code -> {
                if(resultCode == Activity.RESULT_OK && data != null){
                    val tmp =  data.extras
                    val intent = Intent(this, CreateTimelapseActivity::class.java)
                    if(tmp != null){
                        intent.putExtra("PHOTO", tmp.get("data")as Bitmap)
                        startActivity(intent)
                    }

                }
            }
            else -> {
                Toast.makeText(this,"Urecognize request code", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        //menuInflater.inflate(R.menu.bottom_navigation_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.map -> {
            // do stuff
            //Toast.makeText(this, item.itemId, Toast.LENGTH_LONG).show()
            //startActivity(Intent(this, MapsActivity::class.java))
            true
        }
        R.id.search -> {
            //startActivity(Intent(this, SplashActivity::class.java))
            true
        }
        R.id.add_pic -> {
            //startActivity(Intent(this, SplashActivity::class.java))
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun addTimelapse(){
        timelapseclass.timelapse_feed_list.add(Timelapses("Somewhere in the Universe", "#universe #sky #nice", R.drawable.user_icon, R.drawable.timelapse_1, 14, 3, 5))
        timelapseclass.timelapse_feed_list.add(Timelapses("Somewhere in the Universe", "#universe #sky #nice", R.drawable.user_icon, R.drawable.timelapse_2, 11, 0, 4))
        timelapseclass.timelapse_feed_list.add(Timelapses("Somewhere in the Universe", "#universe #sky #nice", R.drawable.user_icon, R.drawable.timelapse_3, 16, 2 ,0))
        timelapseclass.timelapse_feed_list.add(Timelapses("Somewhere in Alps", "#apls #lake #landscape", R.drawable.user_icon, R.drawable.timelapse_4, 2,3,4))
        timelapseclass.timelapse_feed_list.add(Timelapses("Somewhere in Alps", "#apls #lake #landscape", R.drawable.user_icon, R.drawable.timelapse_5, 4, 5,6))
        timelapseclass.timelapse_feed_list.add(Timelapses("Somewhere in Alps", "#apls #lake #landscape", R.drawable.user_icon, R.drawable.timelapse_6, 1,1,0))
        timelapseclass.timelapse_feed_list.add(Timelapses("Somewhere in Alps", "#apls #lake #landscape", R.drawable.user_icon, R.drawable.timelapse_6, 7 ,6 ,5))
    }

    override fun onItemClick(item: Timelapses, position: Int) {
        //Toast.makeText(this, item.name, Toast.LENGTH_LONG).show()
        val intent = Intent(this, TimelapsePageActivity::class.java)
        intent.putExtra("TNAME", item.name)
        intent.putExtra("TDESCR", item.description)
        intent.putExtra("TUSERPIC", item.logo.toString())
        intent.putExtra("TIMAGE", item.tpic.toString())
        intent.putExtra("TLIKES", item.likes)
        intent.putExtra("TSHARES", item.shares)
        intent.putExtra("TCOMM", item.comm)
        startActivity(intent)
        //intent.putExtra("TUSERNAME", item.user_name)
    }

    override fun onCommButtonClick(item: Timelapses, position: Int){
        val intent = Intent(this, CommentsPageActivity::class.java)
        intent.putExtra("TIMAGE", item.tpic.toString())
        intent.putExtra("TNAME", item.name)
        intent.putExtra("TDESCR", item.description)
        startActivity(intent)
    }
}
































