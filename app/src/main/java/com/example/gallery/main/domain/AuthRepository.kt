package com.example.gallery.main.domain
import com.example.gallery.main.domain.model.User
import com.example.gallery.main.utils.Request
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface AuthRepository {
    suspend fun auth(login: String, password: String): Flow<Request<User>>
}