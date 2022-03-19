package com.example.mydairy

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.io.File

class MyAdapter(private val context: Context, private val dataList: ArrayList<DataVo>):
    RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout, parent, false)
        Log.d("온크레이에이트 뷰홀더", "온크레이에이트 뷰홀더")
        return ItemViewHolder(view)

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("온 바인드 뷰 홀더", "온 바인드 뷰 홀더")   //2
        holder.bind(dataList[position], context)

    }

    override fun getItemCount(): Int {
        Log.d("겟 아이템 카운트", "겟 아이템 카운트") // 11
        return dataList.size

    }

}

class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val userPhoto = itemView.findViewById<ImageView>(R.id.bbsImageView)
    private val userContent = itemView.findViewById<TextView>(R.id.titleTextView)
    private val userAddr = itemView.findViewById<TextView>(R.id.addrTextView)
    private val userDate = itemView.findViewById<TextView>(R.id.datetextView)

    fun bind(dataVo: DataVo, context: Context) {
        // 사진
        Log.d("바인드", "바인드")
        Log.d("이미지경로", "${dataVo.imagePath}")
        val file = File(dataVo.imagePath)
        if (file.exists()) {
            val bitmap = BitmapFactory.decodeFile(file.absolutePath)
            userPhoto.setImageBitmap(bitmap)
        }
        /*
        if(dataVo.imagePath != "") {
            Log.d("이미지경로", "${dataVo.imagePath}")
            val file = File("${dataVo.imagePath}")
            val myBitmap = BitmapFactory.decodeFile(file.path)
            userPhoto.setImageBitmap(myBitmap)
        }*/
        /*
        val fExist = file.exists()
        if(fExist) {
            val myBitmap = BitmapFactory.decodeFile("${dataVo.imagePath}")
            Log.d("비트맵", "${myBitmap.toString()}")
            Glide.with(itemView).load(myBitmap.toString()).into(userPhoto)
        }else {
            Log.d("", "이미지 파일 없음")
        }*/
        userContent.text = dataVo.content
        userAddr.text = dataVo.address
        userDate.text = dataVo.date

        itemView.setOnClickListener {
            Intent(context, DetailActivity::class.java).apply {
                putExtra("data", dataVo)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run { context.startActivity(this) }
        }

    }
}
