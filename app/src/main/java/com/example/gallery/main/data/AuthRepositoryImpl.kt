package com.example.gallery.main.data

import com.example.gallery.main.data.mapper.mapToDomain
import com.example.gallery.main.data.model.AuthRequest
import com.example.gallery.main.data.service.AuthService
import com.example.gallery.main.domain.AuthRepository
import com.example.gallery.main.domain.model.TokenStorage
import com.example.gallery.main.domain.model.User
import com.example.gallery.main.utils.Request
import com.example.gallery.main.utils.RequestUtils.reguestFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl @Inject constructor(
    private val authService: AuthService,
    private val tokenStorage: TokenStorage
) : AuthRepository {

    override suspend fun auth(login: String, password: String): Flow<Request<User>> {
        return reguestFlow {
            val authResponse = authService.auth(AuthRequest(login, password))
            val user = authResponse.mapToDomain()
            tokenStorage.saveToken(user.token)
            user
        }
    }
}