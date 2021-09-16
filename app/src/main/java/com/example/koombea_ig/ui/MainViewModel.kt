package com.example.koombea_ig.ui

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koombea_ig.data.network.response.ProfileData
import com.example.koombea_ig.data.network.response.ProfilePost
import com.example.koombea_ig.data.repository.IRemoteRepository
import com.example.koombea_ig.data.repository.picture.IPictureLocalRepository
import com.example.koombea_ig.data.repository.post.IPostLocalRepository
import com.example.koombea_ig.data.repository.user.IUserLocalRepository
import com.example.koombea_ig.utils.DateUtil
import com.example.koombea_ig.utils.DateUtil.toOrdinalDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val applicationContext: Context,
    private val userLocalRepository: IUserLocalRepository,
    private val postLocalRepository: IPostLocalRepository,
    private val pictureLocalRepository: IPictureLocalRepository,
    private val remoteRepository: IRemoteRepository
) : ViewModel() {

    private val TAG = MainViewModel::class.java.name

    val userList = userLocalRepository.getAllUsers()
    val postList = postLocalRepository.getAllPosts()
    val pictureList = pictureLocalRepository.getAllPicture()

    val results = MediatorLiveData<Boolean>()

    init {
        results.postValue(false)
        results.addSource(userList){
            results.value = validateResult()
        }
        results.addSource(postList){
            results.value = validateResult()

        }
        results.addSource(pictureList){
            results.value = validateResult()
        }
    }

    val profileData = MutableLiveData<List<ProfileData>>()

    private fun validateResult(): Boolean{
        return userList.value?.isNotEmpty() == true && postList.value?.isNotEmpty() == true && pictureList.value?.isNotEmpty() == true
    }

    fun populateProfileData(){
        viewModelScope.launch(Dispatchers.IO){
            val users = userList.value ?: return@launch
            val posts = postList.value ?: return@launch
            val pictures = pictureList.value ?: return@launch

            val profile = mutableListOf<ProfileData>()

            users.forEach { user ->

                val profilePost = mutableListOf<ProfilePost>()
                posts.filter { it.userId == user.id }.forEach { post ->
                    val pics = pictures.filter { it.postId == post.id }.map { it.picUrl }

                    profilePost.add(ProfilePost(post.id, post.date.toOrdinalDate(), pics))
                }

                profile.add(ProfileData(user.id, user.name, user.email, user.profilePic, profilePost))
            }
            profileData.postValue(profile)
        }

    }

}