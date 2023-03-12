package com.pientaa.hibernatedemo.author

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "author")
class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,
)