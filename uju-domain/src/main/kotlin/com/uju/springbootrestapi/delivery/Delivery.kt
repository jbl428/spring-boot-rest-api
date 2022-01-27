package com.uju.springbootrestapi.delivery

import com.fasterxml.jackson.annotation.JsonIgnore
import com.uju.springbootrestapi.adderss.Address
import com.uju.springbootrestapi.order.Order
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    var id: Long = 0

    lateinit var a: String

    private lateinit var b: String

    fun getB(): String {
        return this.b
    }

    @JsonIgnore
    @OneToOne(mappedBy = "delivery")
    lateinit var order: Order

    @Embedded
    lateinit var address: Address

    @Enumerated(EnumType.STRING)
    var status: DeliveryStatus = DeliveryStatus.READY
}
