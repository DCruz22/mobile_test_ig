package com.example.koombea_ig.data.repository.user

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.koombea_ig.data.db.KoombeaDatabase
import com.example.koombea_ig.data.models.User
import java.lang.RuntimeException

class UserLocalRepository(private val applicationContext: Context): IUserLocalRepository {
    private fun koombeaDatabase(): KoombeaDatabase? =
        KoombeaDatabase.getInstance(applicationContext)
            ?: throw RuntimeException("No Database selected for current user")

    override suspend fun insertUser(user: User) {
        koombeaDatabase()!!.withTransaction {
            koombeaDatabase()!!.userPostDao().insertUser(user)
        }
    }

    override suspend fun deleteUser(user: User) {
        koombeaDatabase()!!.withTransaction {
            koombeaDatabase()!!.userPostDao().deleteUser(user)
        }
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return koombeaDatabase()!!.userPostDao().getAllUsers()
    }
}