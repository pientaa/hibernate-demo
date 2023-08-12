package com.pientaa.hibernatedemo.article.lazyLoading

import com.pientaa.hibernatedemo.lazyLoading.AuthorRepositoryV2
import com.pientaa.hibernatedemo.lazyLoading.AuthorV2
import com.pientaa.hibernatedemo.lazyLoading.PostRepositoryV2
import com.pientaa.hibernatedemo.lazyLoading.PostV2
import com.pientaa.hibernatedemo.util.TransactionProvider
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class PostAuthorLazyLoadingTest(
    private val postRepository: PostRepositoryV2,
    private val authorRepository: AuthorRepositoryV2,
    private val transactionProvider: TransactionProvider,
) : BehaviorSpec({

    Given("Authors - Jan Nowak and John Smith") {
        val janNowak = AuthorV2(firstName = "Jan", lastName = "Nowak").let { authorRepository.save(it) }

        When("Jan Nowak creates a Post") {
            val postId = PostV2(title = "First Post", content = "Just hanging around", author = janNowak)
                .let { postRepository.save(it) }.id

            Then("Post should be created") {
                transactionProvider.readOnlyTransaction {
                    postRepository.findByIdOrNull(postId)!!.author.firstName shouldBe "Jan"
                }
            }
        }
    }
})