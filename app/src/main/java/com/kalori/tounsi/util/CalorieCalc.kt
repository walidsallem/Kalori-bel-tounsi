package com.kalori.tounsi.util

import com.kalori.tounsi.data.UserProfile
import kotlin.math.roundToInt

object CalorieCalc {
    fun bmr(p: UserProfile): Double {
        val s = if (p.gender.lowercase() == "m") 5 else -161
        return 10 * p.weightKg + 6.25 * p.heightCm - 5 * p.age + s
    }
    fun tdee(p: UserProfile): Int {
        val mult = when (p.activity) {
            "low" -> 1.2
            "med" -> 1.45
            "high" -> 1.7
            else -> 1.2
        }
        return (bmr(p) * mult).roundToInt()
    }
}
