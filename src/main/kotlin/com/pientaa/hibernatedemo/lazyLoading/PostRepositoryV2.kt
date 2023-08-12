package com.pientaa.hibernatedemo.lazyLoading

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepositoryV2 : JpaRepository<PostV2, Long>