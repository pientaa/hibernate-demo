package com.pientaa.hibernatedemo.author

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class ContactInfo(
    @Column(nullable = false)
    val address: String,
    @Column(nullable = false)
    val phone: String,
    @Column(nullable = false)
    val email: String,
)