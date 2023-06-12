package com.sean.msainstagram.user.dto

import io.swagger.v3.oas.annotations.media.Schema

data class UserInfoDto(
    val id: Long,
    val username: String,
    val fullName: String,
    @Schema(name = "biography")
    val bio: String,
    val mediaCount: Int = 0,
    val followerCount: Long = 0,
    val followingCount: Long = 0,
    val isPrivate: Boolean,
) {
}
