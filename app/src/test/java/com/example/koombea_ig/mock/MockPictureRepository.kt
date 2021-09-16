package com.example.koombea_ig.mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koombea_ig.data.models.Picture
import com.example.koombea_ig.data.repository.picture.IPictureLocalRepository

class MockPictureRepository: IPictureLocalRepository {

    private val pictureList = mutableListOf<Picture>(
        Picture(1, "a.jpg", 1),
        Picture(2, "b.jpg", 2),
        Picture(3, "c.jpg", 3),
        Picture(4, "d.jpg", 4),
        Picture(5, "e.jpg", 1),
        Picture(6, "f.jpg", 2),
        Picture(7, "g.jpg", 3),
        Picture(8, "h.jpg", 4),
        Picture(9, "i.jpg", 1),
    )

    override suspend fun insertPicture(picture: Picture) {
        pictureList.add(picture)
    }

    override suspend fun deletePicture(picture: Picture) {
        pictureList.remove(picture)
    }

    override fun getAllPicture(): LiveData<List<Picture>> {
        val pictures = MutableLiveData<List<Picture>>()
        pictures.value = pictureList
        return pictures
    }

    override suspend fun deleteAll() {
        pictureList.clear()
    }
}