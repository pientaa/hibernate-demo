package com.pientaa.hibernatedemo.entity

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    val comments: MutableList<PostComment> = mutableListOf()
) {
    fun addComment(comment: PostComment) {
        comments.add(comment)
        comment.post = this
    }

    fun removeComment(comment: PostComment) {
        comments.remove(comment)
        comment.post = null
    }
}