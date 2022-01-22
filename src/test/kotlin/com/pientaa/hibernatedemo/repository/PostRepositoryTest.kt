package com.pientaa.hibernatedemo.repository

import com.pientaa.hibernatedemo.entity.Post
import com.pientaa.hibernatedemo.entity.PostComment
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostRepositoryTest(
    @Autowired private val postRepository: PostRepository
) {

    @Test
    fun `save one post with three comments`() {
        val comments = mutableListOf(
            PostComment(review = "First review"),
            PostComment(review = "Second review"),
            PostComment(review = "Third review")
        )

        val post = Post(
            title = "First post",
            comments = comments
        )
        postRepository.save(post)
    }
}
