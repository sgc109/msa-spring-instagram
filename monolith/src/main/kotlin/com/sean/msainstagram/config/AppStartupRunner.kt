package com.sean.msainstagram.config

import com.sean.msainstagram.user.entity.User
import com.sean.msainstagram.user.repository.UserRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class AppStartupRunner(
    private val userRepository: UserRepository,
) : ApplicationRunner {
    override fun run(args: ApplicationArguments?) {
        val users = listOf(
            User(name = "sean", fullName = "sean hwang", bio = "Hello, my name is sean hwang"),
            User(name = "stella", fullName = "stella kim", bio = "Hello, my name is stella kim"),
            User(name = "jennie", fullName = "jennie shin", bio = "Hello, my name is jennie shin"),
        )
        users.forEach {
            userRepository.save(it)
        }
    }
}
