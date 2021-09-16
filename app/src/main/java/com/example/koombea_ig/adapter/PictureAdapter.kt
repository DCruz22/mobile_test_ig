package com.example.koombea_ig.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koombea_ig.data.models.Picture
import com.example.koombea_ig.databinding.PicItemBinding

class PictureAdapter(private val context: Context) : RecyclerView.Adapter<PictureAdapter.ViewHolder>() {

    private var pictureList = mutableListOf<Picture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val picture = pictureList[position]
        holder.bindItem(picture)
    }

    fun setItems(items: MutableList<Picture>) {
        this.pictureList = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return pictureList.size
    }

    class ViewHolder(val binding: PicItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bindItem(picture: Picture){
            Glide.with(binding.root)
                .load(picture.picUrl)
                .into(binding.picIv)

        }
    }
}