package com.example.koombea_ig.data.repository.user

import com.example.koombea_ig.data.network.response.DataResponse
import com.example.koombea_ig.data.network.response.ResultWrapper

interface IUserRemoteRepository {
    suspend fun getAllUsersPosts(): DataResponse
}