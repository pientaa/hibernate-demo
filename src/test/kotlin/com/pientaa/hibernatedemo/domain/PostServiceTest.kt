package com.pientaa.hibernatedemo.domain

import com.pientaa.hibernatedemo.entity.Post
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostServiceTest(
    @Autowired private val postService: PostService,
) {
    private val logger = getLogger(javaClass)

    @Test
    fun `add comments to a post`() {
        val postId = postService.save(Post(title = "new post")).id!!

        postService.addComment(comment = "such a great post!", postId = postId)

        postService.getPostWithComments(postId = postId)
            .let { logger.info(it.toString()) }
    }
}
