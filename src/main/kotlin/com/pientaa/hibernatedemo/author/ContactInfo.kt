package com.pientaa.hibernatedemo.author

import jakarta.persistence.Embeddable

@Embeddable
data class ContactInfo(
    var address: String,
    var phone: String,
    var email: String,
)