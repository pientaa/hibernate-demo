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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun save(post: Post) = postRepository.save(post).id!! //should be some custom exception thrown instead

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun addComment(comment: String, postId: Long) {
        postRepository.getReferenceById(postId)
            .apply { addComment(comment) }
            .let { postRepository.save(it) }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteAll() = postRepository.deleteAll()

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteById(postId: Long) = postRepository.deleteById(postId)

    fun getPostWithComments(postId: Long): Post = postRepository.getReferenceById(postId)
}