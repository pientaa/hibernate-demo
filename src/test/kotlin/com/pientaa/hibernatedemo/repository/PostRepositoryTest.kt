package com.pientaa.hibernatedemo.repository

import com.pientaa.hibernatedemo.entity.Post
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class PostRepositoryTest(
    @Autowired private val postRepository: PostRepository
) {

    @Test
    fun foo() {
        postRepository.save(Post())
    }
}
