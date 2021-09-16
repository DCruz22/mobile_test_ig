package com.example.koombea_ig.data.network.response

import com.example.koombea_ig.data.models.Post
import com.squareup.moshi.Json

data class ProfilePost(
    var id: Int = 0,
    var date: String = "",
    @field:Json(name = "pics")
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
