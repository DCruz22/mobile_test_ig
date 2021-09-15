package com.example.koombea_ig.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.koombea_ig.data.models.Post
import com.example.koombea_ig.data.models.User

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

    @Delete
    fun deletePost(post: Post)

    @Query("Select * from post")
    fun getAllPosts(): LiveData<List<Post>>

    @Query("DELETE FROM post")
    fun deleteAll()
}
