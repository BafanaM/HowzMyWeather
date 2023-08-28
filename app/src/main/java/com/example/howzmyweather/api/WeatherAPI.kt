package com.example.howzmyweather.api

import com.example.howzmyweather.model.ForecastResponse
import com.example.howzmyweather.model.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    companion object{
        const val CITY_NAME = "q"
        const val LONGITUDE = "lon"
        const val LATITUDE = "lat"
        const val UNITS = "units"
    }

    @GET("weather?")
    fun getWeatherByGPS(@Query(LATITUDE) latitude: String, @Query(LONGITUDE) longitude: String, @Query(
        UNITS) units: String): Single<WeatherResponse>

    @GET("weather?")
    fun getWeatherCityName(@Query(CITY_NAME) cityName: String, @Query(
        UNITS) units: String): Single<WeatherResponse>

    @GET("forecast?")
    fun getForecastByGPS(@Query(LATITUDE) latitude: String, @Query(LONGITUDE) longitude: String, @Query(
        UNITS) units: String): Single<ForecastResponse>
}