package com.example.koombea_ig.ui

import android.content.DialogInterface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.koombea_ig.R
import com.example.koombea_ig.adapter.PostAdapter
import com.example.koombea_ig.adapter.UserAdapter
import com.example.koombea_ig.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bumptech.glide.Glide
import com.example.koombea_ig.utils.NetworkUtil
import com.example.koombea_ig.worker.WorkerController
import kotlinx.coroutines.runBlocking

import android.view.LayoutInflater
import com.example.koombea_ig.databinding.AlertImageBinding


class MainActivity : AppCompatActivity(), PostAdapter.PictureItemListener {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewBinding.mainPb.visibility = View.VISIBLE

        viewBinding.pullToRefresh.setOnRefreshListener {
            WorkerController.startDownloadWorker(applicationContext)
            viewBinding.pullToRefresh.isRefreshing = false
        }

        setupAdapter()
        setupObservers()
    }

    private fun setupAdapter(){
        val manager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this, this)

        viewBinding.mainRv.layoutManager = manager
        viewBinding.mainRv.adapter = userAdapter
    }

    private fun setupObservers(){
        val isConnected = runBlocking { NetworkUtil.isInternetAvailable() }

        mainViewModel.userList.observe(this, Observer {
            it?.let { users ->
                if (users.isEmpty() && !isConnected){
                    noNetworkErrorDialog()
                }
            }
        })

        mainViewModel.results.observe(this, Observer {
            if (it){
                mainViewModel.populateProfileData()
            }
        })

        mainViewModel.profileData.observe(this, Observer {
            it?.let { profiles ->

                userAdapter.setItems(profiles.toMutableList())
                viewBinding.mainRv.visibility = View.VISIBLE
                viewBinding.mainPb.visibility = View.GONE
            }
        })

    }

    private fun noNetworkErrorDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.network_error_title)
        builder.setMessage(R.string.network_error_message)

        builder.setPositiveButton(R.string.ok) { dialog, which ->

        }
        builder.show()

    }

    override fun onPictureClicked(picUrl: String) {
        val alertDialog = AlertDialog.Builder(this)
        val view = AlertImageBinding.inflate(LayoutInflater.from(this))
        Glide.with(this).load(picUrl).into(view.dialogImageView)
        alertDialog.setView(view.root)
        alertDialog.setNeutralButton(
            R.string.ok
        ) { dlg, sumthin -> }

        alertDialog.show()
    }
}