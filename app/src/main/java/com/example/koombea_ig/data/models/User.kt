package com.example.koombea_ig.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val id: Int = 0,
    var name: String = "",
    var email: String = "",
    var profilePic: String = "",
    var posts: List<Post> = emptyList()
)