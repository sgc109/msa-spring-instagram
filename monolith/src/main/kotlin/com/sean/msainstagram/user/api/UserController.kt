package com.sean.msainstagram.user.api

import com.sean.msainstagram.user.dto.UserInfoDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController {
    @GetMapping("/{userId}/info")
    fun getUserInfo(): UserInfoDto {
        return UserInfoDto(
            username = "",
            fullName = "",
            bio = "",
            mediaCount = 0,
            followerCount = 0,
            followingCount = 0,
            isPrivate = false,
            isVerified = false,
        )
    }
}
