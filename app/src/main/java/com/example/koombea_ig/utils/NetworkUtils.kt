package com.example.koombea_ig.utils

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress

object NetworkUtil {
    const val TAG = "NetworkUtil"
    private const val HOST_TO_CHECK_INTERNET = "google.com"

    suspend fun isInternetAvailable(): Boolean = withContext(Dispatchers.IO) {
        try {
            val ipAddr: InetAddress = InetAddress.getByName(HOST_TO_CHECK_INTERNET)
            //You can replace it with your name
            ipAddr.toString() != ""
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            false
        }
    }
}