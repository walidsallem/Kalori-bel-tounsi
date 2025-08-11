package com.kalori.tounsi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MealEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val foodId: Int,
    val quantity: Float,
    val totalCalories: Int,
    val timestamp: Long
)
