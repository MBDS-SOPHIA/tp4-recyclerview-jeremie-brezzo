package com.openclassrooms.magicgithub.model

data class User(
    val id: String,
    val login: String,
    val avatarUrl: String,
    var isActive: Boolean = false // Add this property
)