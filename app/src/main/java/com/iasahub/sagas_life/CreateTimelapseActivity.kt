package com.iasahub.sagas_life

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.iasahub.sagas_life.databinding.ActivityCreateTimelapseBinding
import kotlinx.android.synthetic.main.activity_create_timelapse.*

class CreateTimelapseActivity : AppCompatActivity() {

    lateinit var binding: ActivityCreateTimelapseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_timelapse)
        photo_img_view.setImageBitmap(getIntent().getParcelableExtra("PHOTO"))


        return_to_cam.setOnClickListener {
            onBackPressed()
        }

        CreateBtnClicking()
    }

    fun CreateBtnClicking() {
        create_timelapse_btn.setOnClickListener {
            val title_res = title_text_inputer.text.toString()
            val tag_res = teg_inputer.text.toString()
            timelapseclass.timelapse_feed_list.add(
                Timelapses(
                    title_res,
                    tag_res,
                    R.drawable.user_icon,
                    R.drawable.timelapse_7,
                    0,
                    0,
                    0
                )
            )
            val intent = Intent(this, TimelapsePageActivity::class.java)
            intent.putExtra("TNAME", title_res)
            intent.putExtra("TDESCR", tag_res)
            intent.putExtra("TUSERPIC", R.drawable.user_icon.toString())
            intent.putExtra("TIMAGE", R.drawable.timelapse_7.toString())
            intent.putExtra("LKES", "0")
            intent.putExtra("SHARES", "0")
            intent.putExtra("COMMS", "0")
            startActivity(intent)
        }
    }

}