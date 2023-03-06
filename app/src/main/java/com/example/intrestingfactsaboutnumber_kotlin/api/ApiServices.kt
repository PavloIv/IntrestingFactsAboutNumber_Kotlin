package com.example.intrestingfactsaboutnumber_kotlin.api

import com.example.intrestingfactsaboutnumber_kotlin.response.FactResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServices {
    @GET("{number}/math?json")
    suspend fun getNumberFact(@Path("number") number: Int): Response<FactResponse>

    @GET("random/math?json")
    suspend fun getRandomFact(): Response<FactResponse>
}