package com.example.koombea_ig.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koombea_ig.data.network.response.ProfileData
import com.example.koombea_ig.data.repository.IRemoteRepository
import com.example.koombea_ig.data.repository.picture.IPictureLocalRepository
import com.example.koombea_ig.data.repository.post.IPostLocalRepository
import com.example.koombea_ig.data.repository.user.IUserLocalRepository

class MainViewModel(
    private val applicationContext: Context,
    private val userLocalRepository: IUserLocalRepository,
    private val postLocalRepository: IPostLocalRepository,
    private val pictureLocalRepository: IPictureLocalRepository,
    private val iRemoteRepository: IRemoteRepository
) : ViewModel() {

    private val TAG = MainViewModel::class.java.name

    val userList = userLocalRepository.getAllUsers()
    val postList = postLocalRepository.getAllPosts()
    val pictureList = pictureLocalRepository.getAllPicture()

    private val profileData = MutableLiveData<List<ProfileData>>()

}