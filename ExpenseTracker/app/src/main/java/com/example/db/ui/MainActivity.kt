package com.example.db.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.*
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.db.databinding.ActivityMainBinding
import com.example.db.db.ExpenseViewModel
import com.example.db.model.Expense

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mExpenseViewModel: ExpenseViewModel

    companion object {
        const val ADD_EXPENSE_REQUEST = 1 // You can use any integer value you prefer
    }

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Creating the viewModel and observing the LiveData to populate the UI
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        mExpenseViewModel.getAll.observe(this, Observer { list ->
            list?.let {
                //Populate the MainActivity's recyclerView
                setRecyclerView(list)
                //See Statistics click handler
                binding.btnSeeReports.setOnClickListener {
                    setStatistics(list)
                }
            }
        })

        //Add New Expense click handler
        binding.btnAddNew.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            //Start a new activity with focus on the result
            startActivityForResult(intent, ADD_EXPENSE_REQUEST)
        }
    }

    /*
    * Calculate different types of statistics such as most expensive expense or most expensive day
    * Once calculated, lunch a new activity
    * Input: A list of expenses
    * Output:
    * */
    private fun setStatistics(list: List<Expense>) {
        val mostExpensiveExpense = getMostExpensive(list)
        val intent = Intent(this, StatisticsActivity::class.java)
        intent.putExtra("MOST_EXPENSIVE", mostExpensiveExpense)
        startActivity(intent)
    }

    /*
    * Finds the expense with the maximum value
    * Input: A list of expenses
    * Output: The most expensive expense
    * */
    private fun getMostExpensive(list: List<Expense>) : Expense{
        var max = -1
        var resultExpense = Expense()
        for (expense in list){
            if(expense.value?.toInt()!! > max){
                max = expense.value.toInt()
                resultExpense = expense
            }
        }
        return resultExpense
    }

    /*
    * Create the recyclerView adapter
    * Input: A list of expenses to be displayed by the recyclerView
    * Output: A populated recyclerView
    * */
    private fun setRecyclerView(list: List<Expense>) {
        val adapter = ExpenseAdapter(list, object : ExpenseAdapter.ButtonClickListener{
            //Handle the click event on the delete button of every item of the recyclerView
            override fun onButtonClicked(position: Int) {
                val expence = list[position]
                mExpenseViewModel.deleteExpense(expence)
            }
        })
        binding.rvOldExpenses.adapter = adapter
        binding.rvOldExpenses.layoutManager = LinearLayoutManager(this)
        binding.rvOldExpenses.hasFixedSize()
    }
}