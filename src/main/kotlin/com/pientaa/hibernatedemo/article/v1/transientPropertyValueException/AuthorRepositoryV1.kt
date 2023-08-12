package com.pientaa.hibernatedemo.article.v1.transientPropertyValueException

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepositoryV1 : JpaRepository<AuthorV1, Long>