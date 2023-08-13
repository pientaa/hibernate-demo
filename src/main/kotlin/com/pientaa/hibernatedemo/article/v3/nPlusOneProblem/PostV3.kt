package com.pientaa.hibernatedemo.article.v3.nPlusOneProblem

import jakarta.persistence.*

@Entity
class PostV3(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var content: String,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableSet<PostCommentV3> = mutableSetOf(),

    @ManyToOne
    val author: AuthorV3,
) {
    fun addComment(content: String, author: AuthorV3) {
        comments.add(
            PostCommentV3(content = content, author = author, post = this)
        )
    }

    fun removeComment(commentId: Long) {
        comments.removeIf { it.id == commentId }
    }
}