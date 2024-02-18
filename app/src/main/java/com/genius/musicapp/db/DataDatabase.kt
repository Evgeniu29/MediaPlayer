package com.genius.musicapp.db
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.genius.musicapp.Data

@Database(entities = [Data::class], version = 1,exportSchema = false)

abstract class DataDatabase: RoomDatabase() {

    abstract fun getAllDataDao(): DataDao

    companion object{

        @Volatile
        private var instance: DataDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DataDatabase::class.java,
            "song_db"
        ).build()



    }

}