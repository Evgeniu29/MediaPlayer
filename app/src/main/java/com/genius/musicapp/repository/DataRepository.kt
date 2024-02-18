package com.genius.musicapp.repository

import com.genius.musicapp.Data
import com.genius.musicapp.db.DataDatabase



class DataRepository(private val db : DataDatabase) {

    //val db = SongDatabase(application)

    suspend fun insertData(data: Data){
        db.getAllDataDao().upsert(data)
    }
    suspend fun deleteData(data: Data) {
        db.getAllDataDao().delete(data)
    }
    fun getSaveData() = db.getAllDataDao().getAllData()

}