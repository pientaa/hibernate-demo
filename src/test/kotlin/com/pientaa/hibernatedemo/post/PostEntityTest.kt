package com.pientaa.hibernatedemo.post

import com.pientaa.hibernatedemo.author.AuthorEntity
import com.pientaa.hibernatedemo.author.AuthorRepository
import com.pientaa.hibernatedemo.util.TransactionProvider
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.hibernate.LazyInitializationException
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@ActiveProfiles("test")
class PostEntityTest(
    private val postRepository: PostRepository,
    private val authorRepository: AuthorRepository,
    private val transactionProvider: TransactionProvider
) : AnnotationSpec() {

    @Test
    fun `create multiple post`() {
        List(10) { postRepository.save(post).id }.distinct().size shouldBe 10
    }

    @Test
    fun `create post`() {
        postRepository.save(post).id shouldNotBe null
    }

    @Test
    @Transactional
    fun `create a post with 3 comments`() {
        val post = post.apply {
            addComment("First comment", author)
            addComment("Second comment", author)
            addComment("Third comment", author)
        }
            .let { postRepository.save(it) }

        post.comments.size shouldBe 3
    }

    @Test
    fun `delete one of 3 existing comments`() {
        //running inside transaction to avoid SELECT before DELETE after removeComment and postRepository.save
        transaction {
            // Given
            val post = post.apply {
                addComment("First comment", author)
                addComment("Second comment", author)
                addComment("Third comment", author)
            }
                .let { postRepository.save(it) }

            // When
            val lastCommentId = post.comments.last().id!!

            post.removeComment(lastCommentId)
            postRepository.save(post)

            // Then
            post.comments.size shouldBe 2
        }
    }

    @Test
    fun `lazy init test, exception should be thrown`() {
        // Given
        val postId = post.apply {
            addComment("First comment", author)
            addComment("Second comment", author)
            addComment("Third comment", author)
        }
            .let { postRepository.save(it) }.id

        // When & Then
        val post = postRepository.findByIdOrNull(postId)!!
        shouldThrow<LazyInitializationException> { post.comments.size }
    }

    @Test
    fun `lazy init test, no exception should be thrown`() {
        // Given
        val postId = post.apply {
            addComment("First comment", author)
            addComment("Second comment", author)
            addComment("Third comment", author)
        }
            .let { postRepository.save(it) }.id

        // When & Then
        transaction(readOnly = true) {
            val post = postRepository.findByIdOrNull(postId)!!

            post.comments.size shouldBe 3
        }
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
    fun `get post, dto-based projection`() {
        // Given
        val postId = post.apply {
            addComment("First comment", author)
            addComment("Second comment", author)
            addComment("Third comment", author)
        }
            .let { postRepository.save(it) }.id!!

        // When
        transaction(readOnly = true) {
            val dto = postRepository.postCommentDto(postId)

            // Then
            dto.size shouldBe 3
        }
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
        get() = PostEntity(title = "Title", content = "Content", author = author, comments = mutableSetOf())
    private val author: AuthorEntity
        get() = authorRepository.findByIdOrNull(1)!!

    private inline fun <reified T> transaction(readOnly: Boolean = false, noinline block: () -> T) =
        when (readOnly) {
            true -> transactionProvider.readOnlyTransaction(block)
            false -> transactionProvider.transaction(block)
        }
}