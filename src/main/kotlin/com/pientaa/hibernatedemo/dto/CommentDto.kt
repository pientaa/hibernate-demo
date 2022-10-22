package com.pientaa.hibernatedemo.dto

import com.pientaa.hibernatedemo.entity.PostComment

data class CommentDto(
    val id: Long,
    val content: String
) {
    constructor(comment: PostComment) : this(
        id = comment.id!!,
        content = comment.content
    )
}