package com.uju.springbootrestapi.order.dto

import com.uju.springbootrestapi.order.Order
import com.uju.springbootrestapi.order.OrderStatus
import com.uju.springbootrestapi.orderItem.OrderItem
import org.apache.tomcat.jni.Address
import java.time.LocalDateTime

data class OrderResponseDto(
    var orderId: Order,
    var name: String,
    var orderDate: LocalDateTime,
    var orderStatus: OrderStatus,
    var address: Address,
    var orderItems: List<OrderItem>
)