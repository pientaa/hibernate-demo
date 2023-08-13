package com.pientaa.hibernatedemo.article.v3.nPlusOneProblem

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PostRepositoryV3 : JpaRepository<PostV3, Long> {
    @Query("select distinct p from PostV3 p left join fetch p.comments where p.id = :postId")
    fun getPostWithComments(@Param("postId") postId: Long): PostV3
}
