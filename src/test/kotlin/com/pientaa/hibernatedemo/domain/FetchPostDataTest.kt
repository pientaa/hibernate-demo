package com.pientaa.hibernatedemo.domain

import com.pientaa.hibernatedemo.entity.Post
import com.pientaa.hibernatedemo.entity.PostComment
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class FetchPostDataTest(
    @Autowired private val postService: PostService,
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @BeforeEach
    fun setUp() {
        logger.info("Set up start")
        val comments = mutableListOf(
            PostComment(review = "First review"),
            PostComment(review = "Second review"),
            PostComment(review = "Third review")
        )

        val post = Post(title = "First post")
            .apply { comments.forEach { addComment(it) } }

        postService.save(post)
        logger.info("Set up stop")
    }

    @AfterEach
    fun cleanUp() {
        logger.info("Clean up start")
        postService.deleteAll()
        logger.info("Clean up stop")
    }

    @Test
    fun `fetch post data`() {
        logger.info("Post title: ${postService.getPostTitleById(1L)}")
        logger.info("Post comments: ${postService.getPostCommentsById(1L)}")
    }
}