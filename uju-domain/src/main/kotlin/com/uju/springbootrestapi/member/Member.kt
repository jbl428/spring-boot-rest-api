package com.uju.springbootrestapi.member

import com.fasterxml.jackson.annotation.JsonIgnore
import com.uju.springbootrestapi.adderss.Address
import com.uju.springbootrestapi.order.Order
import javax.persistence.*

@Entity
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long = 0

    lateinit var name: String

    @Embedded
    lateinit var address: Address

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = mutableListOf()
}
