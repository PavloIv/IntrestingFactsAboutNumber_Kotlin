package com.example.intrestingfactsaboutnumber_kotlin.repository

import com.example.intrestingfactsaboutnumber_kotlin.api.ApiServices
import com.example.intrestingfactsaboutnumber_kotlin.di.ApiModule
import com.example.intrestingfactsaboutnumber_kotlin.response.FactResponse
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiServices: ApiServices,
) {
    suspend fun getNumberFacts(number:Int): Response<FactResponse> {
        return ApiModule.api.getNumberFact(number)
    }
    suspend fun  getRandomFact(): Response<FactResponse> {
        return ApiModule.api.getRandomFact()
    }
}