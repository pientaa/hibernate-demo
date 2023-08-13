package com.pientaa.hibernatedemo.article.v3.nPlusOneProblem

import jakarta.persistence.*

@Entity
class PostCommentV3(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var content: String,

    @ManyToOne
    val author: AuthorV3,

    @ManyToOne
    val post: PostV3
)