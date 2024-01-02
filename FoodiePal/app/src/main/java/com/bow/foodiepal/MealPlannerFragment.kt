package com.bow.foodiepal

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bow.foodiepal.Stores.MealPlanStore
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MealPlannerFragment : Fragment() {

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var datevalue:TextView
    private lateinit var mealssharedPreferences: MealPlanStore
    private lateinit var viewModel: MealPlannerViewModel



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater.inflate(R.layout.fragment_meal_planner, container, false)
        mealssharedPreferences = MealPlanStore(requireContext())
        floatingActionButton = view.findViewById(R.id.addMealPlanButton)
        viewModel = ViewModelProvider(this).get(MealPlannerViewModel::class.java)

        floatingActionButton.setOnClickListener{
            setupFloatingActionButton()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }


    private fun setupdatepicker(dateButton :FloatingActionButton){


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
                     Toast.makeText(context,dateString.toString(),Toast.LENGTH_LONG).show()
                     datevalue.text=dateString.toString()
                 }, year, month, day)
             }

             builder?.show()

        }

    }

    private fun setupFloatingActionButton() {
        floatingActionButton.setOnClickListener {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.activity_add_meal_plan, null)
            val spinner= dialogView.findViewById<Spinner>(R.id.spinner)
            var mealType =""
            context?.let { it1 ->
                ArrayAdapter.createFromResource(
                    it1,
                    R.array.dropdown_items,
                    android.R.layout.simple_spinner_item
                ).also { adapter ->
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter
                }
            }

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    val selectedItem = parent.getItemAtPosition(position).toString()
                     mealType = selectedItem
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    Toast.makeText(context, "Please select a meal type", Toast.LENGTH_SHORT).show()
                }
            }

            val dateButton = dialogView.findViewById<FloatingActionButton>(R.id.button_date_picker)
            datevalue=dialogView.findViewById(R.id.dateselected)
            dateButton.setOnClickListener(){
                setupdatepicker(dateButton)
            }



            val builder = context?.let {
                AlertDialog.Builder(it)
                    .setView(dialogView)
                    .setTitle(R.string.add_new_meal_plan)
                    .setPositiveButton(R.string.save, null)
                    .setNegativeButton(R.string.cancel, null)
            }

            val dialog = builder?.show()

            dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {

                val recipe = dialogView.findViewById<EditText>(R.id.recipe).text.toString()

                if (recipe.isNotBlank()) {

                    viewModel.addMealPlan(MealPlanItem(datevalue.text.toString(), mapOf(mealType to recipe)))
                    dialog.dismiss()
                    setupRecyclerView()
                } else {

                    Toast.makeText(context, R.string.recipe_name_required, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun setupRecyclerView() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.meal_planner_recyclerview)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel.getMealPlanItems().observe(viewLifecycleOwner, Observer { items ->
            mealssharedPreferences.saveMeals(items.toMutableList())
            recyclerView?.adapter = MealPlannerAdapter(mealssharedPreferences.getMeals())
        })
    }

}

