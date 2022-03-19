package com.example.mydocument

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import java.io.File

class MyAdapter{

}
/*

class MyAdapter(private val context: Context, private val dataList: ArrayList<DataVo>):
    RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val userPhoto = itemView.findViewById<ImageView>(R.id.imageView)
    private val userContent = itemView.findViewById<TextView>(R.id.titleTextView)
    private val userAddr = itemView.findViewById<TextView>(R.id.addrTextView)
    private val userDate = itemView.findViewById<TextView>(R.id.datetextView)

    fun bind(dataVo: DataVo) {
        // 사진
        Log.d("이미지경로", "${dataVo.imagePath}")
        val file = File(dataVo.imagePath)
        val fExist = file.exists()
        if(fExist) {
            val myBitmap = BitmapFactory.decodeFile(dataVo.imagePath)
            userPhoto.setImageBitmap(myBitmap)
        }else {
            Log.d("", "이미지 파일 없음")
        }

        userContent.text = dataVo.content
        userAddr.text = dataVo.address
        userDate.text = dataVo.date
    }
}*/
