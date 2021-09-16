package com.example.koombea_ig.data.repository.user

import androidx.lifecycle.LiveData
import com.example.koombea_ig.data.models.User

interface IUserLocalRepository {

    suspend fun insertUser(user: User)

    suspend fun deleteUser(user: User)

    fun getAllUsers(): LiveData<List<User>>

    suspend fun deleteAll()
}