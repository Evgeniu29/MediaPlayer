package com.codingwithjks.musicplayer.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.genius.musicapp.data.Data
import com.genius.musicapp.repository.DataRepository

class DataViewModel : ViewModel(){

    fun insert(context: Context, dataList: ArrayList<Data>)
    {
        DataRepository.insert(context, dataList)
    }

    fun getAllData(context: Context):LiveData<List<Data>>?= DataRepository.getAllData(context)

}