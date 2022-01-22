package com.pientaa.hibernatedemo.domain

import com.pientaa.hibernatedemo.entity.Post
import com.pientaa.hibernatedemo.entity.PostComment
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostServiceTest(
    @Autowired private val postService: PostService,
) {
    private val logger = getLogger(javaClass)

    private val comments = mutableListOf(
        PostComment(review = "First review"),
        PostComment(review = "Second review"),
        PostComment(review = "Third review")
    )

    private val post = Post(title = "First post")

    @AfterEach
    fun cleanUp() {
        logger.info("Clean up start")
        postService.deleteAll()
        logger.info("Clean up stop")
    }

    @Test
    fun `save one post with three comments`() {
        comments.forEach { post.addComment(it) }
        postService.save(post)
    }

    @Test
    fun `save one post with three comments and delete one of the comments`() {
        comments.forEach { post.addComment(it) }
        postService.save(post)

        logger.info("Post saved")

        postService.save(post.apply { removeComment(comments.first()) })
    }
}
