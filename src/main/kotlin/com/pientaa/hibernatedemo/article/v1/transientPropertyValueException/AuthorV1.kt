package com.pientaa.hibernatedemo.article.v1.transientPropertyValueException

import jakarta.persistence.*

@Entity
class AuthorV1(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
)