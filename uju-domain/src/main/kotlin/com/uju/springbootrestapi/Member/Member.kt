package com.uju.springbootrestapi.Member

import com.uju.springbootrestapi.Adderss.Address
import com.uju.springbootrestapi.Order.Order
import org.springframework.transaction.annotation.Transactional
import javax.persistence.*

@Entity
class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long = 0

    var username: String? = null

    @Embedded
    lateinit var address: Address

    @OneToMany(mappedBy = "member")
    var orders: MutableList<Order> = mutableListOf()
}
