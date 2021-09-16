package com.example.koombea_ig.worker

import android.content.Context
import androidx.work.*

object WorkerController {

    fun startDownloadWorker(applicationContext: Context) {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest =
            OneTimeWorkRequestBuilder<DownloadWorker>()
                .setConstraints(constraints)
                .build()

        val workManager = WorkManager.getInstance(applicationContext)

        workManager.beginUniqueWork(
            "user_posts",
            ExistingWorkPolicy.REPLACE,
            workRequest
        ).enqueue()

    }
}