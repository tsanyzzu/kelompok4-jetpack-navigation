package com.example.composenavigationapp.ui.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApiService {
    @GET("weather/{city}")
    suspend fun getWeather(@Path("city") city: String): WeatherResponse
}

object RetrofitInstance {
    private const val BASE_URL = "https://goweather.herokuapp.com/"

    val api: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}