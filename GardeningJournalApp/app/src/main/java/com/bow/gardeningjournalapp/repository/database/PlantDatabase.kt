package com.bow.gardeningjournalapp.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bow.gardeningjournalapp.entity.Plant
import com.bow.gardeningjournalapp.repository.dao.PlantDao

@Database(entities = [Plant::class], version = 2, exportSchema = false)
abstract class PlantDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {
        @Volatile
        private var INSTANCE: PlantDatabase? = null

        fun getDatabase(context: Context): PlantDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDatabase::class.java,
                    "plant_database"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
