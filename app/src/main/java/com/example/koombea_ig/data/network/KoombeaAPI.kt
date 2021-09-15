package com.example.koombea_ig.data.network

import com.example.koombea_ig.data.network.response.DataResponse
import retrofit2.http.GET

interface KoombeaAPI {

    @GET("api/users/posts")
    suspend fun getUsersAndPosts(): DataResponse

}