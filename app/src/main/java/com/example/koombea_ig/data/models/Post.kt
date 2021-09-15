package com.example.koombea_ig.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("userId"),
        onDelete = CASCADE
    )]
)
data class Post(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var date: String = "",
    var userId: String = ""
)
