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
class InitDb (
    private val initService: InitService,
) {

    @PostConstruct
    fun init(): Unit {
        initService.dbInit1()
    }

    companion object {
        @Component
        @Transactional
        class InitService(
            private val em: EntityManager
        ) {
            fun dbInit1(): Unit {
                val member: Member = Member()
                member.name = "userA"
                member.address = Address("서울", "1", "1111")
                em.persist(member)

                val book1: Book = Book()
                book1.name = "JPA1 BOOK"
                book1.price = 10000
                book1.stockQuantity = 100
                em.persist(book1)

                val book2: Book = Book()
                book2.name = "JPA2 BOOK"
                book2.price = 20000
                book2.stockQuantity = 100
                em.persist(book2)

                val orderItem1: OrderItem = OrderItem.createOrderItem(book1, 10000, 1)
                val orderItem2: OrderItem = OrderItem.createOrderItem(book2, 20000, 2)

                val delivery: Delivery = Delivery()
                delivery.address = member.address
                val order: Order = Order.createOrder(member, delivery, orderItem1, orderItem2)
                em.persist(order)
            }
        }
    }
}
