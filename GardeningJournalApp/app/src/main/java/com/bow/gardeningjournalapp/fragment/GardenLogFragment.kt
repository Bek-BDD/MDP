package com.bow.gardeningjournalapp.fragment

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bow.gardeningjournalapp.R
import com.bow.gardeningjournalapp.adapter.PlantAdapter
import com.bow.gardeningjournalapp.entity.Plant
import com.bow.gardeningjournalapp.view.GardenLogViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class GardenLogFragment : Fragment() {

    private lateinit var viewModel: GardenLogViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PlantAdapter
    private lateinit var datevalue: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_garden_log, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(GardenLogViewModel::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val homeBtn = view.findViewById<Button>(R.id.goback)
        val addBtn = view.findViewById<FloatingActionButton>(R.id.add_plant)

        viewModel.allPlants.observe(viewLifecycleOwner, Observer { list->
            adapter = PlantAdapter(list,{plant->savePlant(plant)},{plant->showDetails(plant)})
            recyclerView.adapter = adapter
         })
        homeBtn.setOnClickListener{
            findNavController().navigate(R.id.action_gardenLogFragment_to_homeFragment)
        }
        addBtn.setOnClickListener{
            showAddDialog()
        }

        val samplePlants = mutableListOf<Plant>()


        samplePlants.add(Plant(name = "Rose", type = "Flower", wateringFrequency = "2", plantingDate = "2023-01-01"))
        samplePlants.add(Plant(name = "Tomato", type = "Vegetable", wateringFrequency = "3", plantingDate = "2023-02-15"))
        samplePlants.add(Plant(name = "Basil", type = "Herb", wateringFrequency = "1", plantingDate = "2023-03-10"))

        viewModel.allPlants.observe(viewLifecycleOwner, Observer { list ->
            if(list.isEmpty()){
                for (plant in samplePlants) {
                    viewModel.insert(plant)
                }
            }
        })
    }
    fun savePlant(plant:Plant){
        viewModel.insert(plant);
    }
    fun showDetails(plant:Plant){
       val action =GardenLogFragmentDirections.actionGardenLogFragmentToPlantDetailsFragment(plant.id)
        findNavController().navigate(action)


    }
    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.add_plant, null)


        val dateButton = dialogView.findViewById<FloatingActionButton>(R.id.add_plant)
        datevalue = dialogView.findViewById(R.id.viewTextDate)
        dateButton.setOnClickListener() {
            setupDatePicker(dateButton)
        }
        val builder = context?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
                .setView(dialogView)
                .setTitle("Add Plant")
                .setPositiveButton("Add", null)
                .setNegativeButton("Cancel", null)
        }
        val dialog = builder?.show()
        dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {

            val plantedDate = dialogView.findViewById<TextView>(R.id.viewTextDate).text.toString()
            val plantName = dialogView.findViewById<EditText>(R.id.editTextName).text.toString()
            val plantType = dialogView.findViewById<EditText>(R.id.editTextType).text.toString()
            val w_Freq = dialogView.findViewById<EditText>(R.id.editTextFreq).text.toString()
            println(w_Freq)

            if (plantName.isNotBlank()|| plantType.isNotBlank() || plantedDate.isNotBlank()|| w_Freq.isNotBlank()) {

                viewModel.insert(Plant(name=plantName, type = plantType, wateringFrequency = w_Freq, plantingDate = plantedDate))
                dialog.dismiss()
            } else {

                Toast.makeText(context, "All Fields are required", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun setupDatePicker(dateButton :FloatingActionButton){

        dateButton.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            val builder =
                context?.let { it1 ->
                    DatePickerDialog(it1, DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDayOfMonth ->
                        val selectedDate = Calendar.getInstance()
                        selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth)
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                        val dateString = dateFormat.format(selectedDate.time)
                        Toast.makeText(context,dateString.toString(), Toast.LENGTH_LONG).show()
                        datevalue.text=dateString.toString()
                    }, year, month, day)
                }

            builder?.show()

        }

    }

}
