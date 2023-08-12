package com.pientaa.hibernatedemo.lazyLoading

import jakarta.persistence.*

@Entity
class PostV2(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val author: AuthorV2,
)