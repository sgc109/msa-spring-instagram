package com.sean.msainstagram.user.api

import com.sean.msainstagram.follow.service.FriendshipService
import com.sean.msainstagram.user.dto.UserInfoDto
import com.sean.msainstagram.user.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/users")
class UserController(
    private val userService: UserService,
    private val friendshipService: FriendshipService,
) : UserApi {
    @GetMapping("/info")
    override fun getUserInfo(@RequestParam username: String): UserInfoDto {
        val userInfo = userService.getUserByName(username = username)

        val friendshipInfo = friendshipService.getFriendship(userId = userInfo.id)

        val userInfoWithFollowInfo = with(friendshipInfo) {
            userInfo.copy(
                followerCount = followedByCount,
                followingCount = followCount,
            )
        }

        return userInfoWithFollowInfo
    }
}
