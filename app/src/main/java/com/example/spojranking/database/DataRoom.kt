package com.example.spojranking.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.spojranking.data.User

@Database(entities = [(User::class)], version = 1, exportSchema = false)
abstract class DataRoom : RoomDatabase() {
    abstract fun getDao(): DAO

    companion object {

        private var INSTANCE: DataRoom? = null

        fun getInstance(context: Context): DataRoom {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataRoom::class.java,
                        "database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}