package com.dicoding.picodiploma.submission2_ichsan

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    val retrofit = Retrofit.Builder() //Untuk membuat Instance Retrofit menggunakan Retrofit.builder
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()) //gsonconverter berfungsi untuk menambahkan converter GSON
        .build()

    val apiInstances = retrofit.create(Api::class.java)
}