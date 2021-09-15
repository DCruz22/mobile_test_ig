package com.example.koombea_ig.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.koombea_ig.data.repository.user.IUserLocalRepository
import com.example.koombea_ig.data.repository.user.IUserRemoteRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class DownloadWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams), KoinComponent {

    private val TAG = this::class.java.simpleName
    private val userRemoteRepository: IUserRemoteRepository by inject()
    private val userLocalRepository: IUserLocalRepository by inject()

    private var runAttempt: Int = 0
    private val MAX_RETRY: Int = 1

    override suspend fun doWork(): Result {
        kotlin.runCatching {
            userRemoteRepository.getAllUsersPosts()
        }.onSuccess{ response ->

            response.data.forEach { user ->
                userLocalRepository.insertUser(user)
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