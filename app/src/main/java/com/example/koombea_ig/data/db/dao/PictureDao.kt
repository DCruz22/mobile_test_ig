package com.example.koombea_ig.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.koombea_ig.data.models.Picture
import com.example.koombea_ig.data.models.Post
import com.example.koombea_ig.data.models.User

@Dao
interface PictureDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPicture(picture: Picture)

    @Delete
    fun deletePicture(picture: Picture)

    @Query("Select * from picture")
    fun getAllPicture(): LiveData<List<Picture>>

    @Query("DELETE FROM picture")
    fun deleteAll()
}
