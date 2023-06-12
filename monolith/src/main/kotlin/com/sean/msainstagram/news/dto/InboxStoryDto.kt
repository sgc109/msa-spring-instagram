package com.sean.msainstagram.news.dto

import java.time.Instant

data class InboxStoryDto(
    val storyType: StoryType,
    val args: StoryArgs,
) {
    enum class StoryType(code: Int) {
        TAGGED(102),
        COMMENTED(12),
        COMMENT_LIKED(13),
        FOLLOWED(101),
        STORY_LIKED(651),
        MENTIONED(66),
        MEDIA_LIKED(60),
        FOLLOW_REQUESTED(75),
    }

    data class StoryArgs(
        val destination: String,
        val mediaDestination: String,
        val profileId: String,
        val profileImage: String,
        val profileName: String,
        val secondProfileId: String?,
        val secondProfileImage: String?,
        val text: String,
        val timestamp: Instant,
        val links: List<StoryLink>,
        val media: MediaInfo,
        val commentId: String?,
    )

    data class MediaInfo(
        val id: String,
        val image: String,
    )

    data class StoryLink(
        val id: String,
        val type: String,
        val start: Int,
        val end: Int,
    )
}
