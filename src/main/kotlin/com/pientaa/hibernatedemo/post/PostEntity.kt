package com.pientaa.hibernatedemo.post

import com.pientaa.hibernatedemo.author.AuthorEntity
import jakarta.persistence.*

@Entity
@Table(name = "post")
class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var content: String,

    @ManyToOne(optional = false)
    val author: AuthorEntity
)