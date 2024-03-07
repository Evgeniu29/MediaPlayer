package com.genius.musicapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.inventory.data.DataDatabase
import com.genius.musicapp.data.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataRepository {

    companion object {
        private var dataDatabase: DataDatabase? = null

        private fun initDB(context: Context): DataDatabase? = DataDatabase.getInstance(context)

        fun insert(context: Context, dataList: ArrayList<Data>) {
            dataDatabase = initDB(context)

            GlobalScope.launch(Dispatchers.IO) {

                dataDatabase?.getDataDao()?.insert(dataList)
            }

        }

        fun getAllData(context: Context): LiveData<List<Data>>?
        {
            this.dataDatabase = initDB(context = context)
            return dataDatabase?.getDataDao()?.getAllData()
        }

    }


}