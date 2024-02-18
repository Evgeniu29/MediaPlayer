package com.genius.musicapp.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.genius.musicapp.Data
import com.genius.musicapp.repository.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class DataViewModel(application: Application, private val dataRepository: DataRepository): AndroidViewModel(application) {


    val playlistLiveData = dataRepository.getSaveData()

    fun deleteSong(data: Data) = viewModelScope.launch {
        dataRepository.deleteData(data)
    }

    fun insertSong(data: Data) = viewModelScope.launch {
        dataRepository.insertData(data)
    }


}