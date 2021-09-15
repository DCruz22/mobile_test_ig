package com.example.koombea_ig.data.repository

import com.example.koombea_ig.data.network.response.DataResponse

interface IRemoteRepository {
    suspend fun getRemoteData(): DataResponse
}