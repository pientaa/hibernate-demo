package com.pientaa.hibernatedemo.post

import com.pientaa.hibernatedemo.author.AuthorEntity
import com.pientaa.hibernatedemo.author.AuthorRepository
import com.pientaa.hibernatedemo.util.TransactionProvider
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
    private val transactionProvider: TransactionProvider
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
    fun `get post's author last name`() {
        // Given
        val postId = postRepository.save(post).id!!

        // When
        val authorLastName = transaction(readOnly = true) {
            postRepository.findByIdOrNull(postId)!!.author.lastName
        }

        // Then
        authorLastName shouldBe "Nowak"
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

    private inline fun <reified T> transaction(readOnly: Boolean = false, noinline block: () -> T) =
        when (readOnly) {
            true -> transactionProvider.readOnlyTransaction(block)
            false -> transactionProvider.transaction(block)
        }
}