package com.example.koombea_ig.data.repository.picture

import androidx.lifecycle.LiveData
import com.example.koombea_ig.data.models.Picture

interface IPictureLocalRepository {

    suspend fun insertPicture(picture: Picture)

    suspend fun deletePicture(picture: Picture)

    fun getAllPicture(): LiveData<List<Picture>>

    suspend fun deleteAll()

}
