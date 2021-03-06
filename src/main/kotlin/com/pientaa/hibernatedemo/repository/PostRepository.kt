package com.pientaa.hibernatedemo.repository

import com.pientaa.hibernatedemo.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long>