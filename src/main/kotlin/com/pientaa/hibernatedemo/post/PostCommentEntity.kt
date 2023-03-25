package com.pientaa.hibernatedemo.post

import com.pientaa.hibernatedemo.author.AuthorEntity
import jakarta.persistence.*

@Entity
@Table(name = "post_comment")
class PostCommentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var content: String,

    @ManyToOne(optional = false)
    val author: AuthorEntity,

    @ManyToOne(optional = false)
    val post: PostEntity
)