package com.sean.msainstagram.user.dto

data class AuthorDto(
    val id: String,
    val username: String,
    val fullName: String,
    val profilePicture: String?,
)
