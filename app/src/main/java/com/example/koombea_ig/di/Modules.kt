package com.example.koombea_ig.di

import com.example.koombea_ig.data.network.RetrofigConfig.provideKoombeaApi
import com.example.koombea_ig.data.network.RetrofigConfig.provideOkHttpClient
import com.example.koombea_ig.data.network.RetrofigConfig.provideRetrofit
import com.example.koombea_ig.data.repository.RemoteRepository
import com.example.koombea_ig.data.repository.picture.PictureLocalRepository
import com.example.koombea_ig.data.repository.post.PostLocalRepository
import com.example.koombea_ig.data.repository.user.UserLocalRepository
import com.example.koombea_ig.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object Modules {

    val repositoryModule = module {
        single { UserLocalRepository(get()) }
        single { PostLocalRepository(get()) }
        single { PictureLocalRepository(get()) }
        single { RemoteRepository(get()) }
    }

    private val viewModelModule = module {
        viewModel { MainViewModel(get(), get(), get(), get(), get()) }
    }

    private val networkModule = module {
        factory { provideOkHttpClient() }
        factory { provideKoombeaApi(get()) }
        single { provideRetrofit(get()) }
    }

    val all: List<Module> = listOf(
        repositoryModule,
        viewModelModule,
        networkModule,
    )
}