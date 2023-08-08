package com.pientaa.hibernatedemo.transientPropertyValueException

import jakarta.persistence.*

@Entity
class AuthorV1(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
)