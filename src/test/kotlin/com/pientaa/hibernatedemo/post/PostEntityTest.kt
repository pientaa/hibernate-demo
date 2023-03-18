package com.pientaa.hibernatedemo.post

import com.pientaa.hibernatedemo.author.AuthorEntity
import com.pientaa.hibernatedemo.author.AuthorRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
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
        val postEntity = postRepository.findByIdOrNull(postId)!!

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
    }

    @Test
    fun `delete post`() {
        // Given
        val postId = postRepository.save(post).id!!

        // When
        postRepository.deleteById(postId)

        // Then
        postRepository.findByIdOrNull(postId) shouldBe null
    }

    private val post: PostEntity
        get() = PostEntity(title = "Title", content = "Content", author = author)
    private val author: AuthorEntity
        get() = authorRepository.findByIdOrNull(1)!!
}