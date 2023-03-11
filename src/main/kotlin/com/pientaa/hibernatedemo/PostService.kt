package com.pientaa.hibernatedemo

import org.springframework.stereotype.Service

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun save(post: PostEntity): Long = postRepository.save(post).id ?: throw PostIdNullException()
    fun get(postId: Long): PostEntity = postRepository.getReferenceById(postId)
    fun delete(postId: Long): Unit = postRepository.deleteById(postId)
}

class PostIdNullException : RuntimeException()