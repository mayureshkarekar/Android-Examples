package com.example.mvidemo.data.model

data class Data(
    val data: List<User>,
    val page: Int,
    val perPage: Int,
    val support: Support,
    val total: Int,
    val totalPages: Int
)