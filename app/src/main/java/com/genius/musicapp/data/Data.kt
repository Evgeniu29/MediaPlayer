package com.genius.musicapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.genius.musicapp.Album
import com.genius.musicapp.Artist

@Entity(tableName = "favourite")
data class Data(
    val album: Album,
    val artist: Artist,
    val duration: Int,
    @ColumnInfo(name = "Cover")
    val explicit_content_cover: Int?,
    val explicit_content_lyrics: Int,
    val explicit_lyrics: Boolean,
    @PrimaryKey
    val id: Long?,
    val link: String,
    val md5_image: String,
    val preview: String,
    val rank: Int,
    val readable: Boolean,
    @ColumnInfo(name = "Title")
    val title: String?,
    val title_short: String,
    val title_version: String,
    val type: String
)
