package com.kalori.tounsi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val servingDesc: String,
    val calories: Int
)
