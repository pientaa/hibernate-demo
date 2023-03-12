package com.pientaa.hibernatedemo.post

import com.pientaa.hibernatedemo.author.AuthorEntity
import com.pientaa.hibernatedemo.author.AuthorRepository
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
    private val postRepository: PostRepository,
    private val authorRepository: AuthorRepository,
) : AnnotationSpec() {

    @Test
    fun `create post`() {
        postRepository.save(post).id shouldNotBe null
    }

    @Test
    fun `get post`() {
        // Given
        val postId = postRepository.save(post).id!!

        // When
        val postEntity = postRepository.getReferenceById(postId)

        // Then
        postEntity.title shouldBe "Title"
    }

    @Test
    fun `update post`() {
        // Given
        val postEntity = postRepository.save(post)

        // When
        postRepository.save(postEntity.apply { title = "Updated"; content = "Updated" })

        // Then
        postEntity.title shouldBe "Updated"
        postEntity.author.contactInfo.phone shouldNotBe null
    }

    @Test
    fun `delete post`() {
        // Given
        val postId = postRepository.save(post).id!!

        // When
        postRepository.deleteById(postId)

        // Then
        shouldThrow<JpaObjectRetrievalFailureException> { postRepository.getReferenceById(postId) }
    }

    private val post: PostEntity
        get() = PostEntity(title = "Title", content = "Content", author = author)
    private val author: AuthorEntity
        get() = authorRepository.getReferenceById(1)
}