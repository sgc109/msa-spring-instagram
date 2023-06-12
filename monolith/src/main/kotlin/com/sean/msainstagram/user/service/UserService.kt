package com.sean.msainstagram.user.service

import com.sean.msainstagram.user.domain.User
import com.sean.msainstagram.user.dto.UserInfoDto
import com.sean.msainstagram.user.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
    private val userRepository: UserRepository,
) {
    fun getUserByName(username: String): UserInfoDto {
        return userRepository.findByName(name = username)?.toDto()
            ?: throw IllegalArgumentException("User not found(username=$username)")
    }

    private fun User.toDto() =
        UserInfoDto(
            id = id,
            username = name,
            fullName = fullName,
            bio = bio,
            isPrivate = isPrivate,
        )
}
