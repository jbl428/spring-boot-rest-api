package com.uju.springbootrestapi.order

import com.uju.springbootrestapi.delivery.Delivery
import com.uju.springbootrestapi.delivery.DeliveryStatus
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.orderItem.OrderItem
import java.lang.IllegalStateException
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private lateinit var member: Member

    @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL])
    private var orderItems: MutableList<OrderItem> = mutableListOf()

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "delivery_id")
    private lateinit var delivery : Delivery

    lateinit var orderDate: LocalDateTime

    @Enumerated(EnumType.STRING)
    lateinit var status: OrderStatus

    // 연관관계 메서드
    fun setMember(member: Member): Unit {
        this.member = member
        member.orders.add(this)
    }
    
    fun addOrderItem(orderItem: OrderItem): Unit {
        orderItems.add(orderItem)
        orderItem.order = this
    }

    fun setDelivery(delivery: Delivery): Unit {
        this.delivery = delivery
        delivery.order = this
    }

    // 생성 메서드
    companion object {

        fun createOrder(member: Member, delivery: Delivery, vararg orderItems: OrderItem): Order {
            var order: Order = Order()
            order.member = member
            order.delivery = delivery
            orderItems.forEach { order.addOrderItem(it)}
            order.status = OrderStatus.ORDER
            order.orderDate = LocalDateTime.now()
            return order
        }
    }

    // 비즈니스 로직
    fun cancel(): Unit {
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
