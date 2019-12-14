package com.iasahub.sagas_life

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.content.res.Resources
import kotlinx.android.synthetic.main.layout_comments_item_view.view.*
import kotlinx.android.synthetic.main.layout_item_view.view.*

class CommentsAdapter(var items: ArrayList<Massages>): RecyclerView.Adapter<CommentsViewHolder>(){
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        lateinit var cViewHolder: RecyclerView.ViewHolder
        cViewHolder = CommentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_comments_item_view, parent, false))
        return  cViewHolder
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.initialize(items.get(position))
    }

}

    class CommentsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        var nickName = itemView.nickname
        var time = itemView.time_of_adding
        var userPic = itemView.user_pic_cmnt
        var commentText = itemView.comment_txt
        var like = itemView.like_button

        fun initialize(item: Massages){
            nickName.text = item.username
            time.text = item.time
            userPic.setImageResource(item.Logo)
            commentText.text = item.text
            if(item.liked){
                like.setImageResource(R.drawable.icons_ic_like_empty)
                like.setColorFilter(R.color.red_heart)
            }
            else{

            }
        }
    }