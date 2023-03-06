package com.example.intrestingfactsaboutnumber_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FactsEntity::class], version = 1, exportSchema = false)
abstract class FactsDB: RoomDatabase() {

    abstract fun factsDao(): FactsDao

}