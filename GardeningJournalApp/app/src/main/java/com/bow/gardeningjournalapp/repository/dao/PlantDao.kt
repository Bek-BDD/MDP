package com.bow.gardeningjournalapp.repository.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.bow.gardeningjournalapp.entity.Plant

@Dao
interface PlantDao {
    @Query("SELECT * FROM plants")
    fun getAllPlants(): LiveData<List<Plant>>

    @Insert
    suspend fun insert(plant: Plant): Unit
    @Query("SELECT * FROM plants WHERE id = :plantId")
    fun getPlantById(plantId: Int): LiveData<Plant>

    @Update
    suspend fun update(plant: Plant): Unit
    @Query("DELETE FROM plants WHERE id = :plantId")
    suspend fun deletePlantById(plantId: Int): Unit

    @Delete
    suspend fun delete(plant: Plant): Unit

    @Query("DELETE FROM plants")
    suspend fun deleteAll(): Unit

}