package com.pientaa.hibernatedemo.author

import jakarta.persistence.*

@Entity
@Table(name = "author")
class AuthorEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var firstName: String,
    var lastName: String,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    val contactInfo: ContactInfoEntity
)