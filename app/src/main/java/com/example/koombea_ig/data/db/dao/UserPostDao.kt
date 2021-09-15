package com.example.koombea_ig.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.koombea_ig.data.models.User

@Dao
interface UserPostDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("Select * from user")
    fun getAllUsers(): LiveData<List<User>>

}
