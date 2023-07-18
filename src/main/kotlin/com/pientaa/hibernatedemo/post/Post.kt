package com.pientaa.hibernatedemo.post

import com.pientaa.hibernatedemo.author.Author
import jakarta.persistence.*

@Entity
@Table(name = "post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var title: String,
    @Column(nullable = false)
    var content: String,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableSet<PostComment> = mutableSetOf(),

    @ManyToOne(optional = false)
    val author: Author,
) {
    fun addComment(content: String, author: Author) {
        comments.add(
            PostComment(content = content, author = author, post = this)
        )
    }

    fun removeComment(commentId: Long) {
        comments.removeIf { it.id == commentId }
    }
}