package com.example.koombea_ig.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koombea_ig.data.network.response.ProfileData
import com.example.koombea_ig.databinding.UserItemBinding

class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var profileList = mutableListOf<ProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = profileList[position]
        holder.bindItem(profile)
    }

    fun setItems(items: MutableList<ProfileData>) {
        this.profileList = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return profileList.size
    }

    class ViewHolder(val binding: UserItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(profileData: ProfileData){
            binding.nameTv.text = profileData.name
            binding.emailTv.text = profileData.email
            Glide.with(binding.root)
                .load(profileData.profilePic)
                .circleCrop()
                .into(binding.profilePicIv)

            val layoutManager = LinearLayoutManager(binding.postLayoutRv.context)
            val postAdapter = PostAdapter(binding.postLayoutRv.context)
            binding.postLayoutRv.layoutManager = layoutManager
            binding.postLayoutRv.adapter = postAdapter
            postAdapter.setItems(profileData.posts.toMutableList())

        }
    }
}