package com.pientaa.hibernatedemo

import jakarta.persistence.*

@Entity
@Table(name = "post")
class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var content: String,

    @ManyToOne
    val author: AuthorEntity
)