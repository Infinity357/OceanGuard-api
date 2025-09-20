package com.example.Ocean.Disaster.Survey.util

import com.example.Ocean.Disaster.Survey.database.models.RedditPost

object OceanHazardFilter {
    private val keywords = listOf(
        "tsunami", "hurricane", "typhoon", "cyclone",
        "shipwreck", "sinking", "capsize", "oil spill",
        "flood", "storm surge", "landslide"
    )

    fun isHazard(post: RedditPost): Boolean {
        val combinedText = buildString {
            append(post.title.lowercase())
            post.selftext?.let { append(" ").append(it.lowercase()) }
            append(" ").append(post.url.lowercase())
        }

        return keywords.any { combinedText.contains(it) }
    }
}