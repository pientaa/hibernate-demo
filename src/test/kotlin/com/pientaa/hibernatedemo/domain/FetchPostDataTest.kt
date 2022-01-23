package com.pientaa.hibernatedemo.domain

import com.pientaa.hibernatedemo.repository.PostRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FetchPostDataTest(
    @Autowired private val postRepository: PostRepository,
) {

    @Test
    fun `fetch post data`() {
        val post = postRepository.findByIdOrNull(1L)
        assertTrue(post?.id != null)
    }
}