package com.example.gotapp.network

import com.example.gotapp.model.GoTCharacters
import retrofit2.http.GET

interface APIService {
   @GET("characters")
   suspend fun getCharacters(): GoTCharacters
}