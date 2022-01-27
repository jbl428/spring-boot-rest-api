package com.uju.springbootrestapi.adderss

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Address(
    @Column
    val city: String,
    @Column
    val street: String,
    @Column
    val zipcode: String,
)
