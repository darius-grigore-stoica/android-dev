package com.example.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val value: String? = null,
    val type: String? = null,
    val date: String? = null,
    val company: String? = null,
    ) : Serializable
