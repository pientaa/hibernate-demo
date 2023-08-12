package com.pientaa.hibernatedemo.article.transientPropertyValueException

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepositoryV1 : JpaRepository<PostV1, Long>