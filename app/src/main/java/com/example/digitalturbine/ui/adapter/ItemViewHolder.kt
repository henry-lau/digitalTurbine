package com.example.digitalturbine.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalturbine.R

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var name: TextView = view.findViewById<View>(R.id.tv_name) as TextView
    var numberOfRatings: TextView = view.findViewById<View>(R.id.tv_numberOfRatings) as TextView
    var rating: TextView = view.findViewById<View>(R.id.tv_rating) as TextView
    var photo: ImageView = view.findViewById<View>(R.id.iv_photo) as ImageView
}
