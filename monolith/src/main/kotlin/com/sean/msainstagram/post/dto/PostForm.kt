package com.sean.msainstagram.post.dto

data class PostForm(
    val description: String,
    val slides: List<PostSlideForm>,
)
