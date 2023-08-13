package com.pientaa.hibernatedemo.article.v3.nPlusOneProblem

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepositoryV3 : JpaRepository<AuthorV3, Long>