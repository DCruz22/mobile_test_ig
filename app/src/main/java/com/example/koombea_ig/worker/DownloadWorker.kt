package com.example.koombea_ig.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.koombea_ig.data.models.Picture
import com.example.koombea_ig.data.repository.IRemoteRepository
import com.example.koombea_ig.data.repository.picture.IPictureLocalRepository
import com.example.koombea_ig.data.repository.post.IPostLocalRepository
import com.example.koombea_ig.data.repository.user.IUserLocalRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class DownloadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams), KoinComponent {

    private val TAG = this::class.java.simpleName
    private val remoteRepository: IRemoteRepository by inject()
    private val userLocalRepository: IUserLocalRepository by inject()
    private val postLocalRepository: IPostLocalRepository by inject()
    private val pictureLocalRepository: IPictureLocalRepository by inject()

    private var runAttempt: Int = 0
    private val MAX_RETRY: Int = 1

    override suspend fun doWork(): Result {
        kotlin.runCatching {
            remoteRepository.getRemoteData()
        }.onSuccess{ response ->

            response.data.forEach { profileData ->
                userLocalRepository.insertUser(profileData.toUser())

                profileData.posts.forEach { post ->
                    val localPost = post.toPost(profileData.id)
                    postLocalRepository.insertPost(localPost)

                    post.pictures.forEach{ picture ->
                        pictureLocalRepository.insertPicture(Picture(picUrl = picture, postId = post.id))
                    }
                }
            }

            return Result.success()
        }.onFailure {
            manageFailure()
        }
        return Result.success()
    }

    private fun manageFailure(): Result {
        return if (runAttempt < MAX_RETRY) {
            runAttempt += 1
            Result.retry()
        } else {
            Result.failure()
        }
    }
}