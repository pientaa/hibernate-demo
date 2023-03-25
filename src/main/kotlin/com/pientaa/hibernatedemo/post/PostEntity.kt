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

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<PostCommentEntity>,

    @ManyToOne(optional = false)
    val author: AuthorEntity
) {
    fun addComment(content: String, author: AuthorEntity) {
        this.comments.add(
            PostCommentEntity(content = content, author = author)
        )
    }

    fun removeComment(commentId: Long) {
        this.comments.removeIf { it.id == commentId }
    }
}