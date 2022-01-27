package com.uju.springbootrestapi.order.controller

import com.uju.springbootrestapi.order.Order
import com.uju.springbootrestapi.order.OrderRepository
import com.uju.springbootrestapi.order.OrderSimpleQueryDto
import com.uju.springbootrestapi.order.dto.OrderResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderApiController(
    private val orderRepository: OrderRepository,
) {

    @GetMapping("/api/v1/simple-orders")
    fun orders(): List<OrderSimpleQueryDto>  {
        return orderRepository.findOrderDtos()
    }

    @GetMapping("/api/v2/orders")
    fun orders2(): List<OrderResponseDto> {

        val orders: List<Order> = orderRepository.findAllWithMemberDelivery()
        val result: List<OrderResponseDto> = orders
            .map { it -> OrderResponseDto(it.id, it.member.name, it.orderDate, it.status, it.delivery.address, it.orderItems) }

        return result
    }


}