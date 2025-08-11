package com.kalori.tounsi.data.repo

import android.content.Context
import com.kalori.tounsi.data.*
import com.kalori.tounsi.data.dao.*
import com.kalori.tounsi.data.db.AppDB
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.ZoneId

class Repo(context: Context) {
    private val db = AppDB.get(context)
    val foods = db.foodDao().getAll()
    fun searchFoods(q: String) = db.foodDao().search(q)
    val meals = db.mealDao().getAll()
    val profile = db.profileDao().watch()

    suspend fun upsertProfile(p: UserProfile) = db.profileDao().upsert(p)

    suspend fun addMeal(food: Food, qty: Float) {
        val cals = (food.calories * qty).toInt()
        db.mealDao().insert(
            MealEntry(
                foodId = food.id,
                quantity = qty,
                totalCalories = cals,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun deleteMeal(id: Int) = db.mealDao().deleteById(id)
    suspend fun clearAll() = db.mealDao().clearAll()

    fun todayCalories() = meals.map { list ->
        val startOfDay = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        list.filter { it.timestamp >= startOfDay }.sumOf { it.totalCalories }
    }
}
