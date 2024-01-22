package com.bow.gardeningjournalapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import com.bow.gardeningjournalapp.entity.Plant
import com.bow.gardeningjournalapp.repository.dao.PlantDao
import com.bow.gardeningjournalapp.repository.database.PlantDatabase

class PlantRepository(application: Application) {
    private val plantDao: PlantDao
    val allPlants: LiveData<List<Plant>>

    init {
        val database = PlantDatabase.getDatabase(application)
        plantDao = database.plantDao()
        allPlants = plantDao.getAllPlants()
    }

    suspend fun insert(plant: Plant) {
        plantDao.insert(plant)
    }

    fun getPlantById(plantId: Int):LiveData<Plant> {
       return plantDao.getPlantById(plantId)
    }

    suspend fun update(plant: Plant) {
        plantDao.update(plant)
    }

    suspend fun deleteById(plantId: Int) {
       plantDao.deletePlantById(plantId)
    }
    suspend fun deleteAll() {
        plantDao.deleteAll()
    }
    suspend fun delete(plant: Plant) {
        plantDao.delete(plant)
    }
}
