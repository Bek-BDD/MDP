package com.bow.foodiepal

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.Calendar
import java.util.Locale

class MealPlannerFragment : Fragment() {

    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var mealPlannerAdapter: MealPlannerAdapter
    private lateinit var viewModel: MealPlannerViewModel
    private lateinit var date:String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_meal_planner, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        floatingActionButton = view.findViewById(R.id.addMealPlanButton)
        viewModel = ViewModelProvider(this).get(MealPlannerViewModel::class.java)
        setupRecyclerView()
        setupFloatingActionButton()
        setupdatepicker()
    }

    private fun setupRecyclerView() {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.meal_planner_recyclerview)
        recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel.getMealPlanItems().observe(viewLifecycleOwner, Observer { items ->
            recyclerView?.adapter = MealPlannerAdapter(items)
        })
    }

    private fun setupdatepicker(){

        val dialogView = LayoutInflater.from(context).inflate(R.layout.activity_add_meal_plan, null)
        val dateButton = dialogView.findViewById<FloatingActionButton>(R.id.button_date_picker)

        dateButton.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    { _, year, month, dayOfMonth ->
                        date = String.format(
                            Locale.getDefault(),
                            "%d-%02d-%02d",
                            year,
                            month + 1,
                            dayOfMonth
                        )
                    }, year, month, day
                )
            }?.show()

        }

    }


    private fun setupFloatingActionButton() {
        floatingActionButton.setOnClickListener {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.activity_add_meal_plan, null)
            val builder = context?.let {
                AlertDialog.Builder(it)
                    .setView(dialogView)
                    .setTitle(R.string.add_new_meal_plan)
                    .setPositiveButton(R.string.save, null)
                    .setNegativeButton(R.string.cancel, null)
            }

            val dialog = builder?.show()

            dialog?.getButton(AlertDialog.BUTTON_POSITIVE)?.setOnClickListener {
                val mealType = dialogView.findViewById<EditText>(R.id.mealtype).text.toString()
                val recipe = dialogView.findViewById<EditText>(R.id.recipe).text.toString()

                if (recipe.isNotBlank()) {
                    // recipesList.add(Recipe(recipesList.size, recipeName, 0, cookingTime, rating))
//                adapter.notifyItemInserted(recipesList.size - 1)
                    viewModel.addMealPlan(MealPlanItem(date, mapOf(mealType.capitalize() to recipe)))

                    dialog.dismiss()
                } else {

                    Toast.makeText(context, R.string.recipe_name_required, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

