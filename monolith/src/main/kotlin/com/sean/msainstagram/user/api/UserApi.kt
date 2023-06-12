package com.sean.msainstagram.user.api

import com.sean.msainstagram.user.dto.UserInfoDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "User API")
interface UserApi {
    @Operation(summary = "get user profile info")
    fun getUserInfo(username: String): UserInfoDto
}
