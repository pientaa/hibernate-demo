package com.pientaa.hibernatedemo.article.v2.lazyLoading

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.hibernate.LazyInitializationException
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class PostAuthorLazyLoadingFailingTest(
    private val postRepository: PostRepositoryV2,
    private val authorRepository: AuthorRepositoryV2,
) : BehaviorSpec({

    Given("Author - Jan Nowak") {
        val janNowak = AuthorV2(firstName = "Jan", lastName = "Nowak").let { authorRepository.save(it) }

        When("Jan Nowak creates a Post") {
            val postId = PostV2(title = "First Post", content = "Just hanging around", author = janNowak)
                .let { postRepository.save(it) }.id

            Then("Fetching lazy loaded property should throw LazyInitializationException") {
                shouldThrow<LazyInitializationException> {
                    postRepository.findByIdOrNull(postId)!!.author.firstName shouldBe "Jan"
                }
            }
        }
    }
})