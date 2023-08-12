package com.pientaa.hibernatedemo.lazyLoading

import jakarta.persistence.*

@Entity
class AuthorV2(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
)