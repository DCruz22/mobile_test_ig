package com.example.koombea_ig.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koombea_ig.data.models.User
import com.example.koombea_ig.data.repository.user.IUserLocalRepository
import com.example.koombea_ig.data.repository.user.IUserRemoteRepository

class MainViewModel(private val applicationContext: Context,
private val iUserLocalRepository: IUserLocalRepository,
private val iUserRemoteRepository: IUserRemoteRepository) : ViewModel() {

    private val TAG = MainViewModel::class.java.name

    val userPostList = MutableLiveData<List<User>>()


}