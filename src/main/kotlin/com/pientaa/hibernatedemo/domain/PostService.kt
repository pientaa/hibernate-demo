package com.pientaa.hibernatedemo.domain

import com.pientaa.hibernatedemo.entity.Post
import com.pientaa.hibernatedemo.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository
) {

    fun save(post: Post) = postRepository.save(post).id!! //should be some custom exception thrown instead

    fun addComment(comment: String, postId: Long) {
        postRepository.getReferenceById(postId)
            .apply { addComment(comment) }
            .let { postRepository.save(it) }
    }

    fun deleteAll() = postRepository.deleteAll()

    fun deleteById(postId: Long) = postRepository.deleteById(postId)

    fun getPostWithComments(postId: Long): Post = postRepository.getReferenceById(postId)
}