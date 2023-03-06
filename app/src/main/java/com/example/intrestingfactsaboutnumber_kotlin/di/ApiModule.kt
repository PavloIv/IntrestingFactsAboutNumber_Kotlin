package com.example.intrestingfactsaboutnumber_kotlin.di

import com.example.intrestingfactsaboutnumber_kotlin.api.ApiServices
import com.example.intrestingfactsaboutnumber_kotlin.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @get:Provides
    private val retrofit =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @get:Provides
    val api: ApiServices =
        retrofit.create(ApiServices::class.java)

}