package com.example.koombea_ig.data.network.response

import com.example.koombea_ig.data.models.User
import com.squareup.moshi.Json

data class ProfileData(
    @field:Json(name = "uid")
    val id: String = "",
    var name: String = "",
    var email: String = "",
    @field:Json(name = "profile_pic")
    var profilePic: String = "",
    var posts: List<ProfilePost> = emptyList()
) {

    fun toUser(): User {
        return User(
            id,
            name,
            email,
            profilePic
        )
    }

}