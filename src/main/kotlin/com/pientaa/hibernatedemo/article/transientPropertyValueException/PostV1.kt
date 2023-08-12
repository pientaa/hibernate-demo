package com.pientaa.hibernatedemo.article.transientPropertyValueException

import jakarta.persistence.*

@Entity
class PostV1(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var content: String,

    @ManyToOne
    val author: AuthorV1,
)