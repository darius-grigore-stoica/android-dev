package com.example.db.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.db.model.Expense

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insertExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Query("SELECT * FROM expense")
    fun getAll() : LiveData<List<Expense>>
}