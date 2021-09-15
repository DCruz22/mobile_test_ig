package com.example.koombea_ig.data.models

data class Post(
    val id: Int = 0,
    var date: String = "",
    var pics: List<String> = emptyList()
)
