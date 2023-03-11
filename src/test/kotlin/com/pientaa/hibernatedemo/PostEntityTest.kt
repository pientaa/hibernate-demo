package com.pientaa.hibernatedemo

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class PostEntityTest(
    private val postRepository: PostRepository
) : AnnotationSpec() {

    @Test
    fun `create post`() {
        postRepository.save(PostEntity(title = "Title", content = "Content")).id shouldNotBe null
    }

    @Test
    fun `get post`() {
        // Given
        val postId = postRepository.save(PostEntity(title = "Title", content = "Content")).id!!

        // When
        val postEntity = postRepository.getReferenceById(postId)

        // Then
        postEntity.title shouldBe "Title"
    }

    @Test
    fun `update post`() {
        // Given
        val postId = postRepository.save(PostEntity(title = "Title", content = "Content")).id!!

        // When
        val postEntity = postRepository.save(PostEntity(id = postId, title = "Updated", content = "Updated"))

        // Then
        postEntity.title shouldBe "Updated"
    }

    @Test
    fun `delete post`() {
        // Given
        val postId = postRepository.save(PostEntity(title = "Title", content = "Content")).id!!

        // When
        postRepository.deleteById(postId)

        // Then
        shouldThrow<JpaObjectRetrievalFailureException> { postRepository.getReferenceById(postId) }
    }
}