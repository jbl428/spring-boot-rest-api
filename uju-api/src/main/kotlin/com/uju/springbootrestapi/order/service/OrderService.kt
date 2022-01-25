package com.uju.springbootrestapi.order.service

import com.uju.springbootrestapi.delivery.Delivery
import com.uju.springbootrestapi.item.Item
import com.uju.springbootrestapi.item.ItemRepository
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.member.MemberRepository
import com.uju.springbootrestapi.order.Order
import com.uju.springbootrestapi.order.OrderRepository
import com.uju.springbootrestapi.order.OrderSearch
import com.uju.springbootrestapi.orderItem.OrderItem
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class OrderService(
    private val orderRepository: OrderRepository,
    private val memberRepository: MemberRepository,
    private val itemRepository: ItemRepository
) {

    /**
     * 주문
     */
    @Transactional
    fun order(memberId: Long, itemId: Long, count: Int): Long {
        // 엔티티 조회
        val member: Member = memberRepository.findOne(memberId)
        val item: Item = itemRepository.findOne(itemId)

        // 배송 정보 생성
        val delivery: Delivery = Delivery()
        delivery.address = member.address

        // 주문 상품 생성
        val orderItem: OrderItem = OrderItem.createOrderItem(item, item.price, count)

        // 주문 생성
        val order: Order = Order.createOrder(member, delivery, orderItem)

        // 주문 저장
        orderRepository.save(order)

        return order.id
    }

    // 취소
    @Transactional
    fun cancelOrder(orderId: Long): Unit {
        // 주문 엔티티 조회
        val order: Order = orderRepository.findOne(orderId)

        // 주문 취소
        order.cancel()
    }


    // 검색
    fun findOrders(orderSearch: OrderSearch): List<Order> {
        return orderRepository.findAll(orderSearch)
    }
}