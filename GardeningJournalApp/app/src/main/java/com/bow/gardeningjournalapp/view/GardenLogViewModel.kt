package com.bow.gardeningjournalapp.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bow.gardeningjournalapp.entity.Plant
import com.bow.gardeningjournalapp.repository.PlantRepository
import kotlinx.coroutines.launch

class GardenLogViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PlantRepository = PlantRepository(application)
    val allPlants: LiveData<List<Plant>> = repository.allPlants

    fun insert(plant: Plant) = viewModelScope.launch {
        repository.insert(plant)
    }
    fun getPlantById(plantId: Int):LiveData<Plant> {
       return repository.getPlantById(plantId)
    }
    fun delete(plant: Plant) = viewModelScope.launch {
        repository.delete(plant)
    }

    fun update(plant: Plant) = viewModelScope.launch {
        repository.update(plant)
    }

     fun deleteById(plantId: Int) =viewModelScope.launch {
        repository.deleteById(plantId)
    }
     fun deleteAll() =viewModelScope.launch {
        repository.deleteAll()
    }
}
