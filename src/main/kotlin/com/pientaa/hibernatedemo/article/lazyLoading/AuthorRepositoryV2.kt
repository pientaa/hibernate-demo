package com.pientaa.hibernatedemo.article.lazyLoading

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepositoryV2 : JpaRepository<AuthorV2, Long>