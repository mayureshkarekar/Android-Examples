package com.example.mvidemo.data.api

import com.example.mvidemo.data.api.RetrofitBuilder.GET_USERS
import com.example.mvidemo.data.model.Data
import retrofit2.http.GET

interface APIService {
    @GET(GET_USERS)
    suspend fun getData(): Data
}