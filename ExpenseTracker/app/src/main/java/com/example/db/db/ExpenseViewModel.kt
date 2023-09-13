package com.example.db.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.db.model.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {
    val getAll: LiveData<List<Expense>>
    val repository: ExpenseRepository

    init {
        val userDAO = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(userDAO)
        getAll = repository.getAll
    }

    fun insertExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertExpense(expense)
        }
    }

    fun deleteExpense(expense: Expense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExpense(expense)
        }
    }
}