package com.pientaa.hibernatedemo.author

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class ContactInfo(
    @Column(nullable = false)
    var address: String,
    @Column(nullable = false)
    var phone: String,
    @Column(nullable = false)
    var email: String,
)