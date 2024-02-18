package com.genius.musicapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Insert
import com.genius.musicapp.Data

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(data: Data)

    @Delete
    suspend fun delete(data: Data)

    fun getAllData() : LiveData<List<Data>>

}