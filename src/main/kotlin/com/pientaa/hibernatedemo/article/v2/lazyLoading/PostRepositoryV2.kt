package com.pientaa.hibernatedemo.article.v2.lazyLoading

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepositoryV2 : JpaRepository<PostV2, Long>