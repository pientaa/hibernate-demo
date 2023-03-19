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

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableSet<PostCommentEntity>,

    @ManyToOne(optional = false)
    val author: AuthorEntity,
) {
    fun addComment(content: String, author: AuthorEntity) {
        comments.add(
            PostCommentEntity(content = content, author = author, post = this)
        )
    }

    fun removeComment(commentId: Long) {
        comments.removeIf { it.id == commentId }
    }
}