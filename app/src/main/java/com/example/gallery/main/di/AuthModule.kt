package com.example.gallery.main.di

import com.example.gallery.main.data.AuthRepositoryImpl
import com.example.gallery.main.data.service.AuthService
import com.example.gallery.main.domain.model.TokenStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun provideAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Singleton
    @Provides
    fun providesAuthRepository(
        authService: AuthService,
        tokenStorage: TokenStorage
    ) = AuthRepositoryImpl(authService, tokenStorage)
}