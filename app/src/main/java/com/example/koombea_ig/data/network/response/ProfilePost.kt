package com.example.koombea_ig.data.network.response

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.koombea_ig.data.models.Post

data class ProfilePost(
    var id: Int = 0,
    var date: String = "",
    var pictures: List<String> = emptyList()
) {
    fun toPost(userId: String): Post {
        return Post(
            id,
            date,
            userId
        )
    }
}
