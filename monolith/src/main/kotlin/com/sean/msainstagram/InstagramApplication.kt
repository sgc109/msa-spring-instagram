package com.sean.msainstagram

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class InstagramApplication

fun main(args: Array<String>) {
    runApplication<InstagramApplication>(*args)
}
