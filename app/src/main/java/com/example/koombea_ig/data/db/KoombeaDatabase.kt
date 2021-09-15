package com.example.koombea_ig.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.koombea_ig.data.db.dao.PictureDao
import com.example.koombea_ig.data.db.dao.PostDao
import com.example.koombea_ig.data.db.dao.UserDao
import com.example.koombea_ig.data.models.Picture
import com.example.koombea_ig.data.models.Post
import com.example.koombea_ig.data.models.User
import org.koin.core.KoinComponent

@Database(entities = [User::class, Post::class, Picture::class], version = 3, exportSchema = false)
abstract class KoombeaDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao
    abstract fun pictureDao(): PictureDao

    companion object : KoinComponent {
        private var INSTANCE: KoombeaDatabase? = null

        fun getInstance(context: Context): KoombeaDatabase? {
            if (INSTANCE == null) {
                synchronized(KoombeaDatabase::class) {

                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        KoombeaDatabase::class.java,
                        "user_post.db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }

}