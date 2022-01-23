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
    fun save(post: Post) {
        postRepository.save(post)
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteAll() = postRepository.deleteAll()

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteById(postId: Long) = postRepository.deleteById(postId)
}