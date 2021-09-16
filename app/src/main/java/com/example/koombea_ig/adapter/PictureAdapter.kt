package com.example.koombea_ig.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koombea_ig.data.models.Picture
import com.example.koombea_ig.databinding.PicItemBinding

class PictureAdapter(private val context: Context, private val listener: PostAdapter.PictureItemListener) : RecyclerView.Adapter<PictureAdapter.ViewHolder>() {

    private var pictureList = mutableListOf<Picture>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PicItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, listener)
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

    class ViewHolder(val binding: PicItemBinding, private val listener: PostAdapter.PictureItemListener): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private lateinit var picture: Picture
        init {
            binding.picIv.setOnClickListener(this)
        }

        fun bindItem(picture: Picture){
            this.picture = picture
            Glide.with(binding.root)
                .load(picture.picUrl)
                .into(binding.picIv)

        }

        override fun onClick(p0: View?) {
            listener.onPictureClicked(this.picture.picUrl)
        }
    }
}