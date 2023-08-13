package com.pientaa.hibernatedemo.article.v3.nPlusOneProblem

import jakarta.persistence.*

@Entity
class AuthorV3(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
)