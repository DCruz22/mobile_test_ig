package com.example.koombea_ig.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.koombea_ig.data.models.Picture
import com.example.koombea_ig.data.network.response.ProfileData
import com.example.koombea_ig.data.network.response.ProfilePost
import com.example.koombea_ig.databinding.*

class PostAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var postList = mutableListOf<ProfilePost>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBinding: ViewDataBinding

        when (viewType) {
            1 -> {
                viewBinding =
                    PostItem1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Item1ViewHolder(viewBinding)
            }
            2 -> {
                viewBinding =
                    PostItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Item2ViewHolder(viewBinding)
            }
            3 -> {
                viewBinding =
                    PostItem3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Item3ViewHolder(viewBinding)
            }
            else -> {
                viewBinding =
                    PostItem4Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Item4ViewHolder(viewBinding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = postList[position]

        when (getItemViewType(position)) {
            1 -> {
                (holder as Item1ViewHolder).bindItem(post)
            }
            2 -> {
                (holder as Item2ViewHolder).bindItem(post)
            }
            3 -> {
                (holder as Item3ViewHolder).bindItem(post)
            }
            else -> {
                (holder as Item4ViewHolder).bindItem(post)
            }
        }
    }

    fun setItems(items: MutableList<ProfilePost>) {
        this.postList = items
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val post = postList[position]

        return when (post.pictures.size) {
            1 -> 1
            2 -> 2
            3 -> 3
            else -> 4
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class Item1ViewHolder(private val binding: PostItem1Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(profilePost: ProfilePost) {
            binding.dateTv.text = profilePost.date
            Glide.with(binding.root)
                .load(profilePost.pictures[0])
                .into(binding.pic1Iv)

        }
    }

    class Item2ViewHolder(private val binding: PostItem2Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(profilePost: ProfilePost) {
            binding.dateTv.text = profilePost.date
            Glide.with(binding.root)
                .load(profilePost.pictures[0])
                .into(binding.pic1Iv)
            Glide.with(binding.root)
                .load(profilePost.pictures[1])
                .into(binding.pic2Iv)

        }
    }

    class Item3ViewHolder(private val binding: PostItem3Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(profilePost: ProfilePost) {
            binding.dateTv.text = profilePost.date
            Glide.with(binding.root)
                .load(profilePost.pictures[0])
                .into(binding.pic1Iv)
            Glide.with(binding.root)
                .load(profilePost.pictures[1])
                .into(binding.pic2Iv)
            Glide.with(binding.root)
                .load(profilePost.pictures[2])
                .into(binding.pic3Iv)

        }
    }

    class Item4ViewHolder(private val binding: PostItem4Binding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(profilePost: ProfilePost) {
            binding.dateTv.text = profilePost.date
            Glide.with(binding.root)
                .load(profilePost.pictures[0])
                .into(binding.pic1Iv)

            val layoutManager = GridLayoutManager(binding.pictureGridRv.context,3)
            val pictureAdapter = PictureAdapter(binding.pictureGridRv.context)
            binding.pictureGridRv.layoutManager = layoutManager
            binding.pictureGridRv.adapter = pictureAdapter
            pictureAdapter.setItems(profilePost.pictures.map { Picture(picUrl = it) }.toMutableList())
        }
    }
}