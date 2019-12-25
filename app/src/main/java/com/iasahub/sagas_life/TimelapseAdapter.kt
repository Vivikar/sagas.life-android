package com.iasahub.sagas_life

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_view.view.*

class TimelapseAdapter(
    var items: ArrayList<Timelapses>,
    var clickListener: OnTimelapseItemClickListener
) : RecyclerView.Adapter<TimelapseViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelapseViewHolder {
        lateinit var tViewHolder: RecyclerView.ViewHolder
        tViewHolder = TimelapseViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_item_view,
                parent,
                false
            )
        )
        return tViewHolder
    }

    override fun onBindViewHolder(holder: TimelapseViewHolder, position: Int) {
        holder.initialize(items.get(position), clickListener)
        holder.likeClicking()
    }
}

class TimelapseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var flag = false


    var tName = itemView.tname
    var tDescription = itemView.tdescription
    var user_pic = itemView.user_pic
    var timelapse_pic = itemView.timelapse_pic
    var likes = itemView.like_num
    var shares = itemView.share_num
    var comms = itemView.comm_num


    fun initialize(item: Timelapses, action: OnTimelapseItemClickListener) {
        tName.text = item.name
        tDescription.text = item.description
        user_pic.setImageResource(item.logo)
        timelapse_pic.setImageResource(item.tpic)
        likes.text = item.likes.toString()
        shares.text = item.shares.toString()
        comms.text = item.comm.toString()


        itemView.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }
        itemView.findViewById<ImageButton>(R.id.comm_btn).setOnClickListener {
            action.onCommButtonClick(item, adapterPosition)
        }
    }

    fun likeClicking() {
        //gRPC get liked or not
        //flag = ....
        itemView.findViewById<ImageButton>(R.id.TL_likes).setOnClickListener() {
            if (!flag) {
                likes.text = (likes.text.toString().toInt() + 1).toString()
                flag = true
                // gRPC add like
            } else {
                likes.text = (likes.text.toString().toInt() - 1).toString()
                flag = false
                //gRPC remove like
            }
        }
    }
}

interface OnTimelapseItemClickListener {
    fun onItemClick(item: Timelapses, position: Int)
    fun onCommButtonClick(item: Timelapses, position: Int)

}


























