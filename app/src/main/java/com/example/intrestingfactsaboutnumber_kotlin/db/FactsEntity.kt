package com.example.intrestingfactsaboutnumber_kotlin.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.intrestingfactsaboutnumber_kotlin.utils.FACTS_TABLE

@Entity(tableName = FACTS_TABLE)
data class FactsEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var number: Int = 0,

    var fact: String = ""
)
