package com.example.koombea_ig.data.network.response

import com.example.koombea_ig.data.models.User
import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("uid")
    val id: String = "",
    var name: String = "",
    var email: String = "",
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