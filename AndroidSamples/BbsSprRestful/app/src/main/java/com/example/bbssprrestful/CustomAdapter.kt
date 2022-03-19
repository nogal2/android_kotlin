package com.example.bbssprrestful

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bbssprrestful.dto.BbsDto

class CustomAdapter(val context: Context, val dataList: ArrayList<BbsDto>):RecyclerView.Adapter<CustomAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bbsTitle = itemView.findViewById<TextView>(R.id.bbsTitleTextView)
        val bbsId = itemView.findViewById<TextView>(R.id.bbsIdTextView)
        val bbsReadCount = itemView.findViewById<TextView>(R.id.bbsReadCountTextView)
        val bbsSeq = itemView.findViewById<TextView>(R.id.bbsSeqTextView)

        fun bind(dataVo: BbsDto, context: Context) {
            bbsTitle.text = dataVo.title
            bbsId.text = dataVo.id
            bbsReadCount.text = dataVo.readcount.toString()
            bbsSeq.text = dataVo.seq.toString()

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}