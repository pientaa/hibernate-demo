package com.pientaa.hibernatedemo.author

import jakarta.persistence.*

@Entity
class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var firstName: String,
    @Column(nullable = false)
    var lastName: String,

    @Embedded
    var contactInfo: ContactInfo
)