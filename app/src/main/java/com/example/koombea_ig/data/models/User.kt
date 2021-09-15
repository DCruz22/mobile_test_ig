package com.example.koombea_ig.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
    @PrimaryKey
    @SerializedName("uid")
    val id: String = "",
    var name: String = "",
    var email: String = "",
    var profilePic: String = "",
    var posts: List<Post> = emptyList()
)