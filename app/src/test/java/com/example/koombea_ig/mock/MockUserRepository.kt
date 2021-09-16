package com.example.koombea_ig.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koombea_ig.data.models.User
import com.example.koombea_ig.data.repository.user.IUserLocalRepository

class MockUserRepository : IUserLocalRepository {

    private val userList = mutableListOf<User>(
        User("1", "Dariel", "dcruz@gmai.com", "1.jpg"),
        User("2", "Rosangela", "rmanon@gmai.com", "2.jpg"),
        User("3", "Samuel", "sortiz@gmai.com", "3.jpg")
    )

    override suspend fun insertUser(user: User) {
        userList.add(user)
    }

    override suspend fun deleteUser(user: User) {
        userList.remove(user)
    }

    override fun getAllUsers(): LiveData<List<User>> {
        val users = MutableLiveData<List<User>>()
        users.value = userList
        return users
    }

    override suspend fun deleteAll() {
        userList.clear()
    }
}