package com.uju.springbootrestapi.database

import com.uju.springbootrestapi.adderss.Address
import com.uju.springbootrestapi.delivery.Delivery
import com.uju.springbootrestapi.item.Book
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.order.Order
import com.uju.springbootrestapi.orderItem.OrderItem
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.annotation.PostConstruct
import javax.persistence.EntityManager

@Component
class InitDb(
    private val initService: InitService,
) {

    @PostConstruct
    fun init() {
        initService.dbInit1()
    }

    companion object {
        @Component
        @Transactional
        class InitService(
            private val em: EntityManager
        ) {
            fun dbInit1() {
                val member = Member().apply {
                    name = "userA"
                    address = Address("서울", "1", "1111")
                }
                em.persist(member)

                val book1 = Book().apply {
                    name = "JPA1 BOOK"
                    price = 10000
                    stockQuantity = 100
                }
                em.persist(book1)

                val book2 = Book().apply {
                    name = "JPA2 BOOK"
                    price = 20000
                    stockQuantity = 100
                }
                em.persist(book2)

                val orderItem1 = OrderItem.createOrderItem(book1, 10000, 1)
                val orderItem2 = OrderItem.createOrderItem(book2, 20000, 2)

                val delivery = Delivery()
                delivery.address = member.address
                val order: Order = Order.createOrder(member, delivery, orderItem1, orderItem2)
                em.persist(order)
            }
        }
    }
}
