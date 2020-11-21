package com.example.digitalturbine.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.digitalturbine.R
import com.example.digitalturbine.data.model.db.data.ad
import com.squareup.picasso.Picasso
import java.util.*

class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    private val itemList: MutableList<ad> = ArrayList()
    private var context: Context? = null
    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        context = parent.context
        val view: View = inflater.inflate(R.layout.item_list_row, parent, false)
        val itemViewHolder = ItemViewHolder(view)
        itemViewHolder.itemView.setOnClickListener {
            val adapterPos = itemViewHolder.adapterPosition
            if (adapterPos != RecyclerView.NO_POSITION) {
                onItemClickListener?.onItemClick(adapterPos, itemViewHolder.itemView)
            }
        }
        return itemViewHolder
    }

    fun addItems(itemList: List<ad>?) {
        this.itemList.addAll(itemList!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val ad: ad = itemList[position]
        val viewHolder: ItemViewHolder = holder as ItemViewHolder
        viewHolder.name.text = ad.productName
        viewHolder.numberOfRatings.text = ad.numberOfRatings
        viewHolder.rating.text = ad.rating
        Picasso.get().load(ad.productThumbnail).fit().centerCrop().noFade()
            .into(viewHolder.photo)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}