package com.example.koombea_ig.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koombea_ig.data.models.Post
import com.example.koombea_ig.data.repository.post.IPostLocalRepository

class MockPostRepository: IPostLocalRepository {

    private val postList = mutableListOf<Post>(
        Post(1, "01/02/2021", "1"),
        Post(2, "04/12/2020", "1"),
        Post(3, "01/11/2021", "2"),
        Post(4, "07/21/2021", "3"),
    )

    override suspend fun insertPost(post: Post) {
        postList.add(post)
    }

    override suspend fun deletePost(post: Post) {
        postList.remove(post)
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        val posts = MutableLiveData<List<Post>>()
        posts.value = postList
        return posts
    }

    override suspend fun deleteAll() {
        postList.clear()
    }
}