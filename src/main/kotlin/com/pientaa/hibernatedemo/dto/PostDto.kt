package com.pientaa.hibernatedemo.dto

import com.pientaa.hibernatedemo.entity.Post

data class PostDto(
    val id: Long,
    val title: String,
    val comments: MutableSet<CommentDto>
) {
    constructor(post: Post) : this(
        id = post.id!!,
        title = post.title,
        comments = post.comments.map { CommentDto(it) }.toMutableSet()
    )
}

