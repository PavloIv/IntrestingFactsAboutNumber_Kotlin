package com.example.intrestingfactsaboutnumber_kotlin.repository

import com.example.intrestingfactsaboutnumber_kotlin.db.FactsDao
import com.example.intrestingfactsaboutnumber_kotlin.db.FactsEntity
import javax.inject.Inject

class DatabaseRepository @Inject constructor(private val dao: FactsDao) {

    suspend fun saveFact(entity : FactsEntity) = dao.saveFacts(entity)

    fun getAllFacts() = dao.getAllFacts()
}