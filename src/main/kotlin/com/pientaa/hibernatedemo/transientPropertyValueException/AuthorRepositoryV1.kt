package com.pientaa.hibernatedemo.transientPropertyValueException

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepositoryV1 : JpaRepository<AuthorV1, Long>