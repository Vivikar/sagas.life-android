package com.iasahub.sagas_life

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_view.view.*

class TimelapseAdapter(var items : ArrayList<Timelapses>) : RecyclerView.Adapter<TimelapseViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelapseViewHolder {
        lateinit var tViewHolder: RecyclerView.ViewHolder
        tViewHolder = TimelapseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_view, parent, false))
        return  tViewHolder
    }

    override fun onBindViewHolder(holder: TimelapseViewHolder, position: Int) {
        holder.tName?.text = items.get(position).name
        holder.tDescription?.text = items.get(position).description
        holder.user_pic.setImageResource(items.get(position).logo)
        holder.timelapse_pic.setImageResource(items.get(position).tpic)
    }
}


class TimelapseViewHolder(itemView:View) :RecyclerView.ViewHolder(itemView){
    var tName = itemView.tname
    var tDescription = itemView.tdescription
    var user_pic = itemView.user_pic
    var timelapse_pic = itemView.timelapse_pic

}