package com.uju.springbootrestapi.delivery

import com.uju.springbootrestapi.adderss.Address
import com.uju.springbootrestapi.order.Order
import javax.persistence.*

@Entity
class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    var id: Long = 0

    lateinit var a : String

    private lateinit var b: String

    fun getB():String {return this.b}

    @OneToOne(mappedBy = "delivery")
    lateinit var order: Order

    @Embedded
    lateinit var address: Address

    @Enumerated(EnumType.STRING)
    lateinit var status: DeliveryStatus
}
