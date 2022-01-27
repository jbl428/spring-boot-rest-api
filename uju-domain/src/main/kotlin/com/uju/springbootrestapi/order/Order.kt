package com.uju.springbootrestapi.order

import com.uju.springbootrestapi.delivery.Delivery
import com.uju.springbootrestapi.delivery.DeliveryStatus
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.orderItem.OrderItem
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    lateinit var member: Member

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    var orderItems: MutableList<OrderItem> = mutableListOf()

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    lateinit var delivery: Delivery

    lateinit var orderDate: LocalDateTime

    @Enumerated(EnumType.STRING)
    lateinit var status: OrderStatus

    // 연관관계 메서드
    fun addMemberOrder(member: Member) {
        this.member = member
        member.orders.add(this)
    }

    fun addOrderItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        orderItem.order = this
    }

    fun setDeliveryOrder(delivery: Delivery) {
        this.delivery = delivery
        delivery.order = this
    }

    // 생성 메서드
    companion object {

        fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
            var order: Order = Order()
            order.member = member
            order.delivery = delivery
            orderItems.forEach { order.addOrderItem(it) }
            order.status = OrderStatus.ORDER
            order.orderDate = LocalDateTime.now()
            return order
        }
    }

    // 비즈니스 로직
    fun cancel() {
        if (delivery.status == DeliveryStatus.COMP) {
            throw IllegalStateException("이미 배송완료된 상품은 취소가 불가능합니다.")
        }

        this.status = OrderStatus.CANCEL
        orderItems.forEach { it.cancel() }
    }

    // 조회 로직
    /**
     * 전체 주문 가격 조회
     */
    fun getTotalPrice(): Int {
        var totalPrice: Int = 0
        orderItems.forEach { totalPrice += it.getTotalPrice() }
        return totalPrice
    }
}
