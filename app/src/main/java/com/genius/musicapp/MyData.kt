package com.genius.musicapp

import com.genius.musicapp.data.Data

data class MyData(
    val `data`: List<Data>,
    val next: String,
    val total: Int
)
