package com.pientaa.hibernatedemo.article.v1.transientPropertyValueException

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepositoryV1 : JpaRepository<PostV1, Long>