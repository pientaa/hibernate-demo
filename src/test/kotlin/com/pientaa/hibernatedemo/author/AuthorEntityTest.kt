package com.pientaa.hibernatedemo.author

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class AuthorEntityTest(
    private val authorRepository: AuthorRepository,
) : AnnotationSpec() {

    @Test
    fun `create author`() {
        authorRepository.save(author).id shouldNotBe null
    }

    @Test
    fun `get author`() {
        // Given
        val authorId = authorRepository.save(author).id!!

        // When
        val authorEntity = authorRepository.findByIdOrNull(authorId)!!

        // Then
        authorEntity.lastName shouldBe "Smith"
    }

    @Test
    fun `update author`() {
        // Given
        val authorEntity = authorRepository.save(author)

        // When
        authorRepository.save(authorEntity.apply { firstName = "Updated"; lastName = "Updated" })

        // Then
        authorEntity.lastName shouldBe "Updated"
    }

    @Test
    fun `delete author`() {
        // Given
        val authorId = authorRepository.save(author).id!!

        // When
        authorRepository.deleteById(authorId)

        // Then
        authorRepository.findByIdOrNull(authorId) shouldBe null
    }

    private val author: AuthorEntity
        get() = AuthorEntity(firstName = "John", lastName = "Smith", contactInfo = contactInfo)

    private val contactInfo: ContactInfoEntity
        get() = ContactInfoEntity(address = "Test 1/1 12-345 Poznań", email = "test@test.pl", phone = "+48 512 345 678")
}