package com.example.koombea_ig.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [ForeignKey(
    entity = Post::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("postId"),
    onDelete = ForeignKey.CASCADE
)])
data class Picture(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var picUrl: String = "",
    var postId: Int = 0
)