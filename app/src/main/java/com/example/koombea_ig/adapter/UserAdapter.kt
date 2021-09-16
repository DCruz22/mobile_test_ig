package com.example.koombea_ig.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koombea_ig.data.models.User
import com.example.koombea_ig.databinding.UserItemBinding

class UserAdapter() : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.bindItem(user)
    }

    fun setItems(items: MutableList<User>) {
        this.userList = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(private val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(user: User){
            binding.nameTv.text = user.name
            binding.emailTv.text = user.email
            Glide.with(binding.root)
                .load(user.profilePic)
                .circleCrop()
                .into(binding.profilePicIv)

        }
    }
}