package com.kalori.tounsi.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserProfile(
    @PrimaryKey val id: Int = 1,
    val gender: String,
    val age: Int,
    val heightCm: Int,
    val weightKg: Float,
    val activity: String
)
