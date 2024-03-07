/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.genius.musicapp.data.Data

/**
 * Database class with a singleton INSTANCE object.
 */
@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {

    abstract fun getDataDao():DataDao

    companion object{
        private const val DATABASE_NAME="favourite"
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
                            DATABASE_NAME)
                            .build()
                    }
                }
            }

            return instance
        }
    }
}