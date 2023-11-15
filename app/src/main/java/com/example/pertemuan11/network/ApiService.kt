package com.example.pertemuan11.network

import com.example.pertemuan11.model.Albums
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>
}