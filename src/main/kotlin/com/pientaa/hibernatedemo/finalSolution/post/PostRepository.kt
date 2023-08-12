package com.pientaa.hibernatedemo.finalSolution.post

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    @Query(
        """
        select new com.pientaa.hibernatedemo.post.PostCommentDto(p.id, p.title, pc.id, pc.content) 
        from Post p left join PostComment pc on p.id = pc.post.id
        """
    )
    fun postCommentDto(id: Long): List<PostCommentDto>
}

data class PostCommentDto(
    val id: Long,
    val title: String,
    val commentId: Long?,
    val commentContent: String?,
)