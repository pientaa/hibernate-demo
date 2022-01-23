package com.pientaa.hibernatedemo.entity

import org.hibernate.Hibernate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class PostComment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var review: String,
) {
    @ManyToOne
    lateinit var post: Post

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as PostComment

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()
}