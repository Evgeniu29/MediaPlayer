package com.genius.musicapp

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "music")
data class Album(
    val cover: String,
    val cover_big: String,
    @ColumnInfo(name = "cover_medium")
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: Int,
    val md5_image: String,
    val title: String,
    val tracklist: String,
    val type: String
)