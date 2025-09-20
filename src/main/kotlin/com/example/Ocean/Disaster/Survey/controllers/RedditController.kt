package com.example.Ocean.Disaster.Survey.controllers

import com.example.Ocean.Disaster.Survey.database.models.RedditPost
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import com.example.Ocean.Disaster.Survey.services.RedditService
// import services.RedditService
import com.example.Ocean.Disaster.Survey.util.OceanHazardFilter

/*
@RestController
class RedditController(private val redditService: RedditService) {

    @GetMapping("/api/reddit/hazards")
    fun fetchHazards(
        @RequestParam subreddit: String,
        @RequestParam token: String
    ): List<RedditPost> {
        val listing = redditService.getPosts(subreddit, token)
        val posts = listing?.data?.children?.map { it.data } ?: emptyList()
        return posts.filter { OceanHazardFilter.isHazard(it) }
    }
}
*/


@RestController
class RedditController(private val redditService: RedditService) {

    @GetMapping("/api/public-reddit/hazards")
    fun fetchHazards(
        @RequestParam subreddit: String
    ): List<RedditPost> {
        val listing = redditService.getPosts(subreddit)
        val posts = listing?.data?.children?.map { it.data } ?: emptyList()
        return posts.filter { OceanHazardFilter.isHazard(it) }
    }
}

