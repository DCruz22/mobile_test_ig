package com.example.koombea_ig.data.repository.picture

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.withTransaction
import com.example.koombea_ig.data.db.KoombeaDatabase
import com.example.koombea_ig.data.models.Picture
import java.lang.RuntimeException

class PictureLocalRepository(private val applicationContext: Context): IPictureLocalRepository {
    private fun koombeaDatabase(): KoombeaDatabase? =
        KoombeaDatabase.getInstance(applicationContext)
            ?: throw RuntimeException("No Database selected for current user")

    override suspend fun insertPicture(picture: Picture) {
        koombeaDatabase()!!.withTransaction {
            koombeaDatabase()!!.pictureDao().insertPicture(picture)
        }
    }

    override suspend fun deletePicture(picture: Picture) {
        koombeaDatabase()!!.withTransaction {
            koombeaDatabase()!!.pictureDao().deletePicture(picture)
        }
    }

    override fun getAllPicture(): LiveData<List<Picture>> {
        return koombeaDatabase()!!.pictureDao().getAllPicture()
    }

    override suspend fun deleteAll() {
        koombeaDatabase()!!.pictureDao().deleteAll()
    }
}