package com.example.intrestingfactsaboutnumber_kotlin.response

data class FactResponse(

    val found: Boolean,

    val number: Int,

    val text: String,

    val type: String
)