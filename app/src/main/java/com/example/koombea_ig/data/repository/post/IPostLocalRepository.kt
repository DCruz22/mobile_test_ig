package com.example.koombea_ig.data.repository.post

import androidx.lifecycle.LiveData
import com.example.koombea_ig.data.models.Post

interface IPostLocalRepository {

    suspend fun insertPost(post: Post)

    suspend fun deletePost(post: Post)

    fun getAllPosts(): LiveData<List<Post>>

    suspend fun deleteAll()
}
