package com.sean.msainstagram.user.dto

import io.swagger.v3.oas.annotations.media.Schema

data class UserInfoDto(
    val username: String,
    val fullName: String,
    @Schema(name = "biography")
    val bio: String,
    val mediaCount: Int,
    val followerCount: Long,
    val followingCount: Long,
    val isPrivate: Boolean,
    val isVerified: Boolean,
) {
}
