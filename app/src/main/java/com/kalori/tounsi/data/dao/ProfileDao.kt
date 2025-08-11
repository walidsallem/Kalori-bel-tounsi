package com.kalori.tounsi.data.dao

import androidx.room.*
import com.kalori.tounsi.data.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface ProfileDao {
    @Query("SELECT * FROM UserProfile WHERE id = 1")
    fun watch(): Flow<UserProfile?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(profile: UserProfile)
}
