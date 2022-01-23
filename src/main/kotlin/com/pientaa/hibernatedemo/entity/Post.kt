package com.pientaa.hibernatedemo.entity

import org.hibernate.Hibernate
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String
) {
    @OneToMany(mappedBy = "post", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<PostComment> = mutableListOf()

    fun addComment(comment: PostComment) {
        comments.add(comment)
        comment.post = this
    }

    fun removeComment(comment: PostComment) {
        comments.remove(comment)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Post

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}