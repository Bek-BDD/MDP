package com.bow.gardeningjournalapp.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bow.gardeningjournalapp.R
import com.bow.gardeningjournalapp.entity.Plant
import com.bow.gardeningjournalapp.view.GardenLogViewModel
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PlantDetailsFragment : Fragment() {

    private lateinit var viewModel: GardenLogViewModel
    private var plantId: Int = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_plant_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        plantId = arguments?.getInt("plantId") ?: 0
        viewModel = ViewModelProvider(this).get(GardenLogViewModel::class.java)
        val homeBtn=view.findViewById<Button>(R.id.goback)
        val updateBtn=view.findViewById<Button>(R.id.update)
        val deleteBtn =view.findViewById<Button>(R.id.delete)


        viewModel.getPlantById(plantId).observe(viewLifecycleOwner) { plant ->
            plant?.let { displayPlantDetails(it) }
        }

        homeBtn.setOnClickListener{
            findNavController().navigate(R.id.action_plantDetailsFragment_to_gardenLogFragment)
        }
        updateBtn.setOnClickListener{
            viewModel.getPlantById(plantId).observe(viewLifecycleOwner){plant ->
                plant?.let {showUpdateDialog(it)}
            }
        }
        deleteBtn.setOnClickListener{
            viewModel.deleteById(plantId)
            Toast.makeText(context,"Deleted Plant with id: ${plantId}",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_plantDetailsFragment_to_gardenLogFragment)
        }
    }

    private fun displayPlantDetails(plant: Plant) {
            val plantNameTextView = view?.findViewById<TextView>(R.id.plantName)
            val plantTypeTextView = view?.findViewById<TextView>(R.id.plantType)
            val wateringFrequencyTextView = view?.findViewById<TextView>(R.id.frequency)
            val plantingDateTextView = view?.findViewById<TextView>(R.id.plantedDate)

            plantNameTextView?.text = plant.name
            plantTypeTextView?.text =  "${plant.type}"
            wateringFrequencyTextView?.text = "Watering every ${plant.wateringFrequency} days"
            plantingDateTextView?.text = "Planted on: ${plant.plantingDate}"

    }
    private fun showUpdateDialog(plant: Plant){
        val dialogView = LayoutInflater.from(context).inflate(R.layout.update_plant, null)
        val editTextName: EditText = dialogView.findViewById(R.id.editTextName)
        val editTextType: EditText = dialogView.findViewById(R.id.editTextType)
        val editTextFreq: EditText = dialogView.findViewById(R.id.editTextFreq)
        val viewTextDate: TextView = dialogView.findViewById(R.id.viewTextDate)
        editTextName.setText(plant.name)
        editTextType.setText(plant.type)
        editTextFreq.setText(plant.wateringFrequency.toString())
        viewTextDate.text=plant.plantingDate

        val builder = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
                .setView(dialogView)
                .setTitle("Update Plant")
                .setPositiveButton("Update", null)
                .setNegativeButton("Cancel", null)
        }


        val dialog = builder?.show()

        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
            val updatedName = editTextName.text.toString()
            val updatedType = editTextType.text.toString()
            val updateFreq =editTextFreq.text.toString()

            val updatedPlant = plant.copy(name = updatedName, type = updatedType, wateringFrequency = updateFreq)
            viewModel.update(updatedPlant)
            dialog.cancel()
            findNavController().navigate(R.id.action_plantDetailsFragment_to_gardenLogFragment)
        }
        dialog?.getButton(AlertDialog.BUTTON_NEGATIVE)?.setOnClickListener{ dialog.cancel() }

    }


}
