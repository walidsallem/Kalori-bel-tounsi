package com.kalori.tounsi.data.dao

import androidx.room.*
import com.kalori.tounsi.data.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {
    @Query("SELECT * FROM Food ORDER BY name ASC")
    fun getAll(): Flow<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(foods: List<Food>)

    @Query("SELECT * FROM Food WHERE name LIKE '%' || :q || '%' ORDER BY name ASC")
    fun search(q: String): Flow<List<Food>>
}
