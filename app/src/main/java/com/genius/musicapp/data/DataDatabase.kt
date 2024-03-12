package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.genius.musicapp.data.Data

@Database(entities = arrayOf(Data::class), version = 1)
        abstract class DataDatabase : RoomDatabase() {

    abstract fun getDataDao():DataDao

    companion object{
        @Volatile
        private var instance:DataDatabase?=null

        fun getInstance(context: Context):DataDatabase?{
            if(instance == null)
            {
                synchronized(DataDatabase::class.java)
                {
                    if(instance == null)
                    {
                        instance=Room.databaseBuilder(context,DataDatabase::class.java,
                            "music").build()
                    }
                }
            }

            return instance
        }
    }
}