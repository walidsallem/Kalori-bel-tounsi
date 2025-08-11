package com.kalori.tounsi.data.dao

import androidx.room.*
import com.kalori.tounsi.data.MealEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Query("SELECT * FROM MealEntry ORDER BY timestamp DESC")
    fun getAll(): Flow<List<MealEntry>>

    @Insert
    suspend fun insert(entry: MealEntry)

    @Query("DELETE FROM MealEntry WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM MealEntry")
    suspend fun clearAll()
}
