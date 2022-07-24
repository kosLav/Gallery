package com.example.gallery.main.domain.model

import android.content.Context

data class UserInfo(
    val id: String,
    val phone: String,
    val email:  String,
    val firstName: String,
    val lastName: String,
    val avatar: String,
    val city: String,
    val about:  String
)