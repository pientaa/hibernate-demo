package com.pientaa.hibernatedemo

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException

@SpringBootTest
class PostEntityTest(
    @Autowired private val postRepository: PostRepository
) {
    @Test
    fun `post create test`() {
        PostEntity(title = "new post", content = "wow, such a content")
            .let { postRepository.save(it) }
    }

    @Test
    fun `post get test`() {
        assertNotNull(postRepository.getReferenceById(1).content)
    }

    @Test
    fun `post delete test`() {
        postRepository.deleteById(1)
        assertThrows<JpaObjectRetrievalFailureException> { postRepository.getReferenceById(1) }
    }
}
