package com.pientaa.hibernatedemo

import jakarta.persistence.*

@Entity
@Table(name = "author")
class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
)