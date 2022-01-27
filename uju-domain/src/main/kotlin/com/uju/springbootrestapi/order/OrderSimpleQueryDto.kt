package com.uju.springbootrestapi.order

import com.uju.springbootrestapi.adderss.Address
import java.time.LocalDateTime

data class OrderSimpleQueryDto(
    var orderId: Long,
    var name: String,
    var orderDate: LocalDateTime,
    var orderStatus: OrderStatus,
    var address: Address
)
