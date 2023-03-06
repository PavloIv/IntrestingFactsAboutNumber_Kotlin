package com.example.intrestingfactsaboutnumber_kotlin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.intrestingfactsaboutnumber_kotlin.utils.FACTS_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface FactsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFacts(factsEntity: FactsEntity)

    @Query("SELECT * FROM $FACTS_TABLE ORDER BY id DESC")
    fun getAllFacts(): Flow<MutableList<FactsEntity>>
}