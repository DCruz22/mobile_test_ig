package com.example.koombea_ig.data.repository.user

import com.example.koombea_ig.data.network.KoombeaAPI
import com.example.koombea_ig.data.network.response.DataResponse
import com.example.koombea_ig.data.network.response.ResultWrapper

class UserRemoteRepository(private val koombeaAPI: KoombeaAPI): IUserRemoteRepository {

    override suspend fun getAllUsersPosts(): DataResponse {
        return koombeaAPI.getUsersAndPosts()
    }
}