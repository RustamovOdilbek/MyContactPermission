package com.uzb.permissionsmycontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val activity: MainActivity, val items: ArrayList<Contact>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact_list,parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        if (holder is ContactViewHolder){
            val tv_user_name = holder.tv_user_name
            val tv_user_number = holder.tv_user_number
            val iv_call = holder.iv_call
            val iv_sms = holder.iv_sms

            tv_user_name.text = item.name
            tv_user_number.text = item.mobileNumber

            iv_call.setOnClickListener { activity.calltheUser(item.mobileNumber) }

            iv_sms.setOnClickListener { activity.openSmsActivity(item.name!!, item.mobileNumber!!) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ContactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tv_user_name: TextView
        val tv_user_number: TextView
        val iv_call: ImageView
        val iv_sms: ImageView

        init {
            tv_user_name = view.findViewById(R.id.tv_user_name)
            tv_user_number = view.findViewById(R.id.tv_user_number)
            iv_call = view.findViewById(R.id.iv_call)
            iv_sms = view.findViewById(R.id.iv_sms)
        }
    }
}
