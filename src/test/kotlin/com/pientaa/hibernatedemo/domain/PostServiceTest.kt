package com.pientaa.hibernatedemo.domain

import com.pientaa.hibernatedemo.entity.Post
import org.junit.jupiter.api.Assertions.assertTrue
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
    fun `add a post with 2 comments`() {
        val postId: Long = postService.save(
            Post(title = "new post", content = "wow, such a content")
        )

        postService.addComment(comment = "such a great post!", postId = postId)
        postService.addComment(comment = "nah, nothing special at all", postId = postId)
    }

    @Test
    fun `hashCode test`() {
        val post = Post(title = "new post", content = "wow, such a content")

        val hashSet = hashSetOf(post)

        postService.save(post)

        assertTrue(post in hashSet)
    }
}
