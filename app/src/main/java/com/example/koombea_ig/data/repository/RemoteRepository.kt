package com.example.koombea_ig.data.repository

import com.example.koombea_ig.data.network.KoombeaAPI
import com.example.koombea_ig.data.network.response.DataResponse

class RemoteRepository(private val koombeaAPI: KoombeaAPI): IRemoteRepository {

    override suspend fun getRemoteData(): DataResponse {
        return koombeaAPI.getUsersAndPosts()
    }
}