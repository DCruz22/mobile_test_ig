package com.example.koombea_ig.adapter

import android.content.Context
import android.icu.lang.UCharacter
import android.view.LayoutInflater
import android.view.View
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

class PostAdapter(private val context: Context, private val listener: PictureItemListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var postList = mutableListOf<ProfilePost>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewBinding: ViewDataBinding

        when (viewType) {
            1 -> {
                viewBinding =
                    PostItem1Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Item1ViewHolder(viewBinding, listener)
            }
            2 -> {
                viewBinding =
                    PostItem2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Item2ViewHolder(viewBinding, listener)
            }
            3 -> {
                viewBinding =
                    PostItem3Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Item3ViewHolder(viewBinding, listener)
            }
            else -> {
                viewBinding =
                    PostItem4Binding.inflate(LayoutInflater.from(parent.context), parent, false)
                return Item4ViewHolder(viewBinding, listener)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = postList[position]

        when (getItemViewType(position)) {
            0 -> {

            }
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
            0 -> 0
            1 -> 1
            2 -> 2
            3 -> 3
            else -> 4
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    class Item1ViewHolder(private val binding: PostItem1Binding, private val listener: PictureItemListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var post: ProfilePost

        init {
            binding.pic1Iv.setOnClickListener(this)
        }

        fun bindItem(profilePost: ProfilePost) {
            this.post = profilePost
            binding.dateTv.text = profilePost.date
            Glide.with(binding.root)
                .load(profilePost.pictures[0])
                .into(binding.pic1Iv)

        }

        override fun onClick(p0: View?) {
            if(p0?.id != binding.dateTv.id){
                listener.onPictureClicked(this.post.pictures[0])
            }
        }
    }

    class Item2ViewHolder(private val binding: PostItem2Binding, private val listener: PictureItemListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private lateinit var post: ProfilePost

        init {
            binding.pic1Iv.setOnClickListener(this)
            binding.pic2Iv.setOnClickListener(this)
        }

        fun bindItem(profilePost: ProfilePost) {
            this.post = profilePost
            binding.dateTv.text = profilePost.date
            Glide.with(binding.root)
                .load(profilePost.pictures[0])
                .into(binding.pic1Iv)
            Glide.with(binding.root)
                .load(profilePost.pictures[1])
                .into(binding.pic2Iv)
        }

        override fun onClick(p0: View?) {
            when(p0?.id){
                binding.pic1Iv.id -> listener.onPictureClicked(this.post.pictures[0])
                binding.pic2Iv.id -> listener.onPictureClicked(this.post.pictures[1])
            }
        }
    }

    class Item3ViewHolder(private val binding: PostItem3Binding, private val listener: PictureItemListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var post: ProfilePost

        init {
            binding.pic1Iv.setOnClickListener(this)
            binding.pic2Iv.setOnClickListener(this)
            binding.pic3Iv.setOnClickListener(this)
        }

        fun bindItem(profilePost: ProfilePost) {
            this.post = profilePost
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

        override fun onClick(p0: View?) {
            when(p0?.id){
                binding.pic1Iv.id -> listener.onPictureClicked(this.post.pictures[0])
                binding.pic2Iv.id -> listener.onPictureClicked(this.post.pictures[1])
                binding.pic3Iv.id -> listener.onPictureClicked(this.post.pictures[2])
            }
        }
    }

    class Item4ViewHolder(private val binding: PostItem4Binding, private val listener: PictureItemListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private lateinit var post: ProfilePost

        init {
            binding.pic1Iv.setOnClickListener(this)
        }

        fun bindItem(profilePost: ProfilePost) {
            this.post = profilePost
            val picturesLength = profilePost.pictures.size -1
            binding.dateTv.text = profilePost.date
            Glide.with(binding.root)
                .load(profilePost.pictures[0])
                .into(binding.pic1Iv)

            val layoutManager = LinearLayoutManager(binding.pictureGridRv.context, LinearLayoutManager.HORIZONTAL, false)
            val pictureAdapter = PictureAdapter(binding.pictureGridRv.context, listener)
            binding.pictureGridRv.layoutManager = layoutManager
            binding.pictureGridRv.adapter = pictureAdapter

            val pictures = profilePost.pictures.map { Picture(picUrl = it) }.takeLast(picturesLength)
            pictureAdapter.setItems(pictures.toMutableList())
        }

        override fun onClick(p0: View?) {
            when(p0?.id){
                binding.pic1Iv.id -> listener.onPictureClicked(this.post.pictures[0])
            }
        }
    }

    interface PictureItemListener {
        fun onPictureClicked(picUrl: String)
    }
}