package com.example.koombea_ig.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.koombea_ig.data.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User?)

    @Delete
    fun deleteUser(user: User)

    @Query("Select * from user")
    fun getAllUsers(): LiveData<List<User>>

    @Query("DELETE FROM user")
    fun deleteAll()
}
