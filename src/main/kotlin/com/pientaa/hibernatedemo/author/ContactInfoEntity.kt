package com.pientaa.hibernatedemo.author

import jakarta.persistence.*

@Entity
@Table(name = "contact_info")
class ContactInfoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(nullable = false)
    var address: String,
    @Column(nullable = false)
    var phone: String,
    @Column(nullable = false)
    var email: String,
)