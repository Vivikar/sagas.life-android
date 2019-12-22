package com.iasahub.sagas_life

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_comments_item_view.view.*

class CommentsAdapter(var items: ArrayList<Massages>, var clickListener: OnSettingsClickListener) :
    RecyclerView.Adapter<CommentsViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentsViewHolder {
        lateinit var cViewHolder: RecyclerView.ViewHolder
        cViewHolder = CommentsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_comments_item_view,
                parent,
                false
            )
        )
        return cViewHolder
    }

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.initialize(items.get(position), clickListener)
        holder.LikeClicking(items.get(position))
    }

}

class CommentsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var nickName = itemView.nickname
    var time = itemView.time_of_adding
    var userPic = itemView.user_pic_cmnt
    var commentText = itemView.comment_txt
    var like = itemView.like_button

    fun initialize(item: Massages, action: OnSettingsClickListener) {
        nickName.text = item.username
        time.text = item.time
        userPic.setImageResource(item.Logo)
        commentText.text = item.text
        if (item.liked) {
            like.setImageResource(R.drawable.icons_ic_like_filled)
            ImageViewCompat.setImageTintList(
                like,
                ColorStateList.valueOf(Color.parseColor("#d15247"))
            )
        }

        itemView.findViewById<ImageButton>(R.id.comments_settings_btn).setOnClickListener {
            action.SettingsClicking(
                item,
                adapterPosition,
                itemView.findViewById<ImageButton>(R.id.comments_settings_btn)
            )
        }
    }

    fun LikeClicking(item: Massages) {
        itemView.findViewById<ImageButton>(R.id.like_button).setOnClickListener {
            if (item.liked) {
                like.setImageResource(R.drawable.icons_ic_like_empty)
                ImageViewCompat.setImageTintList(
                    like,
                    ColorStateList.valueOf(Color.parseColor("#808080"))
                )
                item.liked = false
                //send grpc
            } else {
                like.setImageResource(R.drawable.icons_ic_like_filled)
                ImageViewCompat.setImageTintList(
                    like,
                    ColorStateList.valueOf(Color.parseColor("#d15247"))
                )
                item.liked = true
                //send grpc
            }
        }
    }
}

interface OnSettingsClickListener {
    fun SettingsClicking(item: Massages, position: Int, btn: ImageButton)
}