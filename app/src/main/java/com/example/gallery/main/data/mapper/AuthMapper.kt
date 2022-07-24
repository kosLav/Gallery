package com.example.gallery.main.data.mapper

import com.example.gallery.main.data.model.AuthResponse
import com.example.gallery.main.domain.model.User
import com.example.gallery.main.domain.model.UserInfo

fun AuthResponse.mapToDomain(): User {
    return User(
        this.token,
        UserInfo(
            this.userInfo.id,
            this.userInfo.phone,
            this.userInfo.email,
            this.userInfo.firstName,
            this.userInfo.lastName,
            this.userInfo.avatar,
            this.userInfo.city,
            this.userInfo.about
        )
    )
}