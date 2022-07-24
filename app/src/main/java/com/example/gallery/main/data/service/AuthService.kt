package com.example.gallery.main.data.service

import com.example.gallery.main.data.model.AuthRequest
import com.example.gallery.main.data.model.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

    @POST("auth/login")
    suspend fun auth(@Body authRequest: AuthRequest): AuthResponse

}