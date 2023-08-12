package com.pientaa.hibernatedemo.post

import com.pientaa.hibernatedemo.author.Author
import jakarta.persistence.*

@Entity
@Table(name = "post_comment")
class PostComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var content: String,

    @ManyToOne(optional = false)
    val author: Author,

    @ManyToOne(optional = false)
    val post: Post
)