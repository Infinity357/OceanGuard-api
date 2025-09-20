package com.example.Ocean.Disaster.Survey.database.models

data class RedditListing(
    val kind: String,
    val data: RedditData
)

data class RedditData(
    val after: String?,
    val dist: Int?,
    val children: List<RedditChild>
)

data class RedditChild(
    val kind: String,
    val data: RedditPost
)

data class RedditPost(
    val title: String,
    val selftext: String?,     // text body if self post
    val url: String,           // link or image url
    val subreddit: String,
    val author: String,
    val permalink: String,
    val created_utc: Long,
    val is_self: Boolean,
    val post_hint: String?,    // "image", "link", "video", null
    val thumbnail: String?     // preview image if available
)

