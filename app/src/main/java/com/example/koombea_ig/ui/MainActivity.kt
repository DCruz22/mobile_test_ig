package com.example.koombea_ig.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koombea_ig.R
import com.example.koombea_ig.adapter.UserAdapter
import com.example.koombea_ig.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinding.mainPb.visibility = View.VISIBLE

        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter(){
        val manager = LinearLayoutManager(this)
        userAdapter = UserAdapter()

        viewBinding.mainRv.layoutManager = manager
        viewBinding.mainRv.adapter = userAdapter
    }

    private fun setupObservers(){

        mainViewModel.userList.observe(this, Observer {
            it?.let { users ->
                userAdapter.setItems(users.toMutableList())
                viewBinding.mainRv.visibility = View.VISIBLE
                viewBinding.mainPb.visibility = View.GONE
            }
        })

    }
}