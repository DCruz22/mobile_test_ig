package com.example.koombea_ig.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.koombea_ig.data.network.response.ProfileData

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var profilePic: String = ""
){
    protected fun parseUserPost(profileData: ProfileData): User{
        return profileData.let {
            User(
                id,
                name,
                email,
                profilePic
            )
        }
    }
}