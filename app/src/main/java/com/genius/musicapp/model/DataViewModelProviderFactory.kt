package com.genius.musicapp.model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.genius.musicapp.repository.DataRepository

class DataViewModelProviderFactory (val app: Application, private val dataRepository: DataRepository):
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DataViewModel(app, dataRepository) as T
    }
}