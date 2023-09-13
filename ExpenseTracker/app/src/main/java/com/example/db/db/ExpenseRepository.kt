package com.example.db.db

import androidx.lifecycle.LiveData
import com.example.db.model.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    val getAll : LiveData<List<Expense>> = expenseDao.getAll()

    suspend fun insertExpense(expense: Expense){
        expenseDao.insertExpense(expense)
    }

    suspend fun deleteExpense(expense: Expense){
        expenseDao.deleteExpense(expense)
    }
}