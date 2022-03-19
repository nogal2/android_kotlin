package com.example.sample30

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(private val context:Context, private val dataList: ArrayList<DataVo>): RecyclerView.Adapter<ItemViewHolder>() {

    //여기 있는 쪽은 내부에서만 사용하는 것.
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

//dto와 비슷한 개념
class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val userPhoto = itemView.findViewById<ImageView>(R.id.img_profile)
    private val userName = itemView.findViewById<TextView>(R.id.userNameTxt)
    private val userPay = itemView.findViewById<TextView>(R.id.payTxt)
    private val userAddress = itemView.findViewById<TextView>(R.id.addressTxt)

    // data -> resource 를 하는 곳(binding)
    fun bind(dataVo:DataVo, context: Context) {
        //사진
        if(dataVo.photo != "") {
            val resourceId = context.resources.getIdentifier(dataVo.photo, "drawable", context.packageName)

            if(resourceId > 0) {
                userPhoto.setImageResource(resourceId)
            } else { // resourceId가 0보다 크면 제대로 안넘어왔다는 얘기라서 다른 처리를 해줘야 함.
                Glide.with(itemView).load(dataVo.photo).into(userPhoto)
                //userPhoto.setImageResource(R.mipmap.ic_launcher_round)
            }
        } else {    // 문자열이 제대로 안넘어왔을때
            userPhoto.setImageResource(R.mipmap.ic_launcher_round)  // 이미지가 없다. 지원되는 아무 이미지나 뿌리라는 코드.
        }

        //TextView 데이터를 세팅
        userName.text = dataVo.name
        userPay.text = dataVo.pay.toString()
        userAddress.text = dataVo.address

        // itemView를 클릭시
        itemView.setOnClickListener {
            println(dataVo.name + " " + dataVo.photo)

            // ProfileDetailActivity 로 이동
            Intent(context, ProfileDetailActivity::class.java).apply {  // 이동하라는 코드

                // 짐쌓는것.(spring의 경우 model)
                putExtra("data", dataVo)

                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // 새로운 activity를 추가해라. (원래 view위에 덮어씌우는 것)

            }.run {context.startActivity(this)}
        }

    }

}