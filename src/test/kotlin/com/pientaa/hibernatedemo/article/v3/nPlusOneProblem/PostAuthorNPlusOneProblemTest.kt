package com.pientaa.hibernatedemo.article.v3.nPlusOneProblem

import com.pientaa.hibernatedemo.util.TransactionProvider
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class PostAuthorNPlusOneProblemTest(
    private val postRepository: PostRepositoryV3,
    private val authorRepository: AuthorRepositoryV3,
) : BehaviorSpec({

    Given("Post added by Jan Nowak") {
        val janNowak = AuthorV3(firstName = "Jan", lastName = "Nowak").let { authorRepository.save(it) }

        val post = PostV3(title = "First Post", content = "Just hanging around", author = janNowak)
            .let { postRepository.save(it) }

        When("Jan Nowak adds 3 comments to the Post") {
            val postId = post.apply {
                addComment("First comment", author)
                addComment("Second comment", author)
                addComment("Third comment", author)
            }
                .let { postRepository.save(it) }.id!!

            Then("Comments collection should be fetched") {
                postRepository.getPostWithComments(postId).comments.size shouldBe 3
            }
        }
    }
})
