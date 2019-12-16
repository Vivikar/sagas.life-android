package com.iasahub.sagas_life

import android.graphics.Bitmap

data class Timelapses(var name: String, var description: String, var logo: Int, var tpic: Int, var likes: Int, var shares: Int, var comm: Int){
}

data class  Massages(var username: String, var text: String, var Logo: Int, var time: String, var liked: Boolean){
}

public class timelapseclass(){
    public companion object{ lateinit var timelapse_feed_list: ArrayList<Timelapses>}
}
public class massageclass(){
    public companion object{ lateinit var massage_feed_list: ArrayList<Massages>}
}