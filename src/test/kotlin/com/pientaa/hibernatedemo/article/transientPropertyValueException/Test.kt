package com.pientaa.hibernatedemo.article.transientPropertyValueException

import com.pientaa.hibernatedemo.transientPropertyValueException.AuthorV1
import com.pientaa.hibernatedemo.transientPropertyValueException.PostV1
import com.pientaa.hibernatedemo.transientPropertyValueException.PostRepositoryV1
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
class Test(
    private val postRepository: PostRepositoryV1
) : BehaviorSpec({

    Given("Author") {
        val author = AuthorV1(firstName = "Jan", lastName = "Nowak")

        When("Author creates a post") {
            val post = PostV1(title = "First Post", content = "Just hanging around", author = author)
            val postId = postRepository.save(post).id

            Then("Post is created") {
                postRepository.findByIdOrNull(postId) shouldNotBe null
            }
        }
    }
})