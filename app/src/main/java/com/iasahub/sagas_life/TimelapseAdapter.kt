package com.iasahub.sagas_life

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }
}

class TimelapseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tName = itemView.tname
    var tDescription = itemView.tdescription
    var user_pic = itemView.user_pic
    var timelapse_pic = itemView.timelapse_pic


    fun initialize(item: Timelapses, action: OnTimelapseItemClickListener) {
        tName.text = item.name
        tDescription.text = item.description
        user_pic.setImageResource(item.logo)
        timelapse_pic.setImageResource(item.timelapsePic)

        itemView.setOnClickListener {
            action.onItemClick(item, adapterPosition)
        }
    }
}

interface OnTimelapseItemClickListener {
    fun onItemClick(item: Timelapses, position: Int)
}


























