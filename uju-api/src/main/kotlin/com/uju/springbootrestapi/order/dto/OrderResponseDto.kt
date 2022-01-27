package com.uju.springbootrestapi.order.dto

import com.uju.springbootrestapi.adderss.Address
import com.uju.springbootrestapi.order.OrderStatus
import com.uju.springbootrestapi.orderItem.OrderItem
import java.time.LocalDateTime

data class OrderResponseDto(
    var orderId: Long,
    var name: String,
    var orderDate: LocalDateTime,
    var orderStatus: OrderStatus,
    var address: Address,
    var orderItems: List<OrderItem>
)

data class OrderItemDto(
    var name: String,
    var orderPrice: Int,
    var count: Int
)
