package com.example.account

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.account.vo.Account

class CustomAdapter(private val context:Context, private val dataList: ArrayList<Account>): RecyclerView.Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.view_selects_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(dataList[position], context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class ItemViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {
    private val userSeq = itemView.findViewById<TextView>(R.id.seqTxt)
    private val userIcog = itemView.findViewById<TextView>(R.id.icogTxt)
    private val userTitle = itemView.findViewById<TextView>(R.id.titleTxt)
    private val userDate = itemView.findViewById<TextView>(R.id.dateTxt)
    private val userAmount = itemView.findViewById<TextView>(R.id.amountTxt)

    fun bind(vo: Account, context: Context) {

        userSeq.text = vo.seq.toString()
        userIcog.text = vo.icOg
        userTitle.text = vo.title
        userDate.text = vo.date
        userAmount.text = vo.Amount.toString()

        itemView.setOnClickListener {
            Intent(context, SelectDetailActivity::class.java).apply {

                putExtra("data", vo)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run {context.startActivity(this)}
        }
    }
}