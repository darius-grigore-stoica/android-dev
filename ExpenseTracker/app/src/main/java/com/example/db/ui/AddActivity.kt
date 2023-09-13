package com.example.db.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.db.databinding.ActivityAddBinding
import com.example.db.model.Expense
import com.example.db.db.ExpenseViewModel

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Create a new viewModel
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        //Handle the add expense logic
        binding.btnAddNewExpense.setOnClickListener {
            //Checks the input to be correct (all the fields not to be empty)
            if(inputCheck(binding.etValue, binding.etType, binding.etDate, binding.etCompany)){
                val expense = Expense(
                    value = binding.etValue.text.toString(),
                    type = binding.etType.text.toString(),
                    date = binding.etDate.text.toString(),
                    company = binding.etCompany.text.toString())
                mExpenseViewModel.insertExpense(expense)
                setResult(Activity.RESULT_OK)
                finish()
            } else {
                Toast.makeText(this@AddActivity, "Invalid Data. Try Again!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /*
    * Checks the input to be valid (all the fields not to be empty)
    * Input: EditTexts from the activity_add.xml
    * Output: true if the input is valid, false otherwise
    * */
    private fun inputCheck(etValue: EditText, etType: EditText, etDate: EditText, etCompany: EditText): Boolean {
        val date = etDate.text.toString()
        val isValidValue = (etValue.text.toString().toInt() > 0)
        val isValidDate = (date[2] == '/' && date[5] == '/')
        return (isValidDate &&
                isValidValue &&
                !(etValue.text.isEmpty() || etType.text.isEmpty() || etDate.text.isEmpty() || etCompany.text.isEmpty()))
    }
}