package com.example.intrestingfactsaboutnumber_kotlin.di

import android.content.Context
import androidx.room.Room
import com.example.intrestingfactsaboutnumber_kotlin.db.FactsDB
import com.example.intrestingfactsaboutnumber_kotlin.db.FactsEntity
import com.example.intrestingfactsaboutnumber_kotlin.utils.FACTS_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, FactsDB::class.java, FACTS_DATABASE
    ).allowMainThreadQueries()
        .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideDao(db: FactsDB) = db.factsDao()

    @Provides
    fun provideEntity() = FactsEntity()
}