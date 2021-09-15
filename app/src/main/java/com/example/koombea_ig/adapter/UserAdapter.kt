package com.example.koombea_ig.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koombea_ig.data.models.User

class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = UserItemBinding
//        return ViewHolder()
        TODO("Not yet implemented")

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    fun setItems(items: MutableList<User>) {
        this.userList = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}