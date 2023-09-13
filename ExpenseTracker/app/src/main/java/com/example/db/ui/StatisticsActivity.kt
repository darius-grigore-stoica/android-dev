package com.example.db.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.db.R
import com.example.db.databinding.ActivityStatisticsBinding
import com.example.db.model.Expense

@Suppress("DEPRECATION")
class StatisticsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStatisticsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatisticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val value = binding.tvValue
        val type = binding.tvType
        val date = binding.tvDate
        val company = binding.tvCompany
        if(intent != null){
            val expense : Expense? = intent.getSerializableExtra("MOST_EXPENSIVE") as? Expense
            value.text = expense?.value
            type.text = expense?.type
            date.text = expense?.date
            company.text = expense?.company
        }
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}