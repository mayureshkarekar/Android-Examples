package com.example.mvidemo.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    // region constants
    const val BASE_URL = "https://reqres.in/api/"
    const val GET_USERS = "users"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = getRetrofit().create(APIService::class.java)
    //endregion
}