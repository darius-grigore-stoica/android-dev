package com.example.db.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.db.R
import com.example.db.model.Expense

class ExpenseAdapter(private val expenses: List<Expense>, private val btnClickListener: ButtonClickListener) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>(){
    interface ButtonClickListener {
        fun onButtonClicked(position: Int)
    }

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.itemView.apply {
            val tvValue = findViewById<TextView>(R.id.tvValue)
            val tvType = findViewById<TextView>(R.id.tvType)
            val tvDate = findViewById<TextView>(R.id.tvDate)
            val tvCompany = findViewById<TextView>(R.id.tvCompany)

            tvValue.text = expense.value
            tvDate.text = expense.date
            tvCompany.text = expense.company
            tvType.text = expense.type

            val btnDelete = findViewById<Button>(R.id.btnDelete)
            btnDelete.setOnClickListener{
                btnClickListener.onButtonClicked(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return expenses.size
    }
}