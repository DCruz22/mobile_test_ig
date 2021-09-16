package com.example.koombea_ig.data.repository.post

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.koombea_ig.data.db.KoombeaDatabase
import com.example.koombea_ig.data.models.Post
import java.lang.RuntimeException

class PostLocalRepository(private val applicationContext: Context): IPostLocalRepository {
    private fun koombeaDatabase(): KoombeaDatabase? =
        KoombeaDatabase.getInstance(applicationContext)
            ?: throw RuntimeException("No Database selected for current user")

    override suspend fun insertPost(post: Post) {
        koombeaDatabase()!!.withTransaction {
            koombeaDatabase()!!.postDao().insertPost(post)
        }
    }

    override suspend fun deletePost(post: Post) {
        koombeaDatabase()!!.withTransaction {
            koombeaDatabase()!!.postDao().deletePost(post)
        }
    }

    override fun getAllPosts(): LiveData<List<Post>> {
        return koombeaDatabase()!!.postDao().getAllPosts()
    }

    override suspend fun deleteAll() {
        koombeaDatabase()!!.postDao().deleteAll()
    }
}