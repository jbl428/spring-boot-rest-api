package com.uju.springbootrestapi.order.service

import com.uju.springbootrestapi.ApiApplication
import com.uju.springbootrestapi.adderss.Address
import com.uju.springbootrestapi.exception.NotEnoughStockException
import com.uju.springbootrestapi.item.Book
import com.uju.springbootrestapi.item.Item
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.order.Order
import com.uju.springbootrestapi.order.OrderRepository
import com.uju.springbootrestapi.order.OrderStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest(classes = [ApiApplication::class])
@Transactional
internal class OrderServiceTest @Autowired constructor(
    private val em: EntityManager,
    private val orderService: OrderService,
    private val orderRepository: OrderRepository
){

    @Test
    fun `상품 주문`() {
        // given
        val member: Member = createMember()

        val book: Book = createBook("시골 JPA", 10000, 10)

        val orderCount: Int = 2
        // when
        val orderId: Long = orderService.order(member.id, book.id, orderCount)

        // then
        val getOrder: Order = orderRepository.findOne(orderId)

        assertEquals(OrderStatus.ORDER, getOrder.status, "상품 주문시 상태는 ORDER")
        assertEquals(1, getOrder.orderItems.size, "주문한 상품 종류 사가 정확해야한다.")
        assertEquals(10000 * orderCount, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다.")
        assertEquals(8, book.stockQuantity, "주문 수량만큼 재고가 줄어야한다.")
    }

    @Test
    fun `주문 취소`() {
        // given
        val member: Member = createMember()
        val item: Book = createBook("시골 JPA", 10000, 10)

        val orderCount: Int = 2

        val orderId: Long = orderService.order(member.id, item.id, orderCount)
        // when
        orderService.cancelOrder(orderId)

        // then
        val getOrder: Order = orderRepository.findOne(orderId)

        assertEquals(OrderStatus.CANCEL, getOrder.status, "주문 취소 시 상태는 cancel이다.")
        assertEquals(10, item.stockQuantity, "주문이 취소된 상품은 그만큼 재고가 증가해야한다.")
    }

    @Test()
    fun `상품 주문 재고 수량 초과`() {
        // given
        val member: Member = createMember()
        val item: Item = createBook("시골 JPA", 10000, 10)

        val orderCount: Int = 11
        // when

        // then
        val thrown: Throwable = assertThrows(NotEnoughStockException::class.java) {
            orderService.order(member.id, item.id, orderCount)
        }

        assertEquals("need more stock", thrown.message)
    }

    private fun createBook(name: String, price: Int, stockQuantity: Int): Book {
        val book: Book = Book()
        book.name = name
        book.price = price
        book.stockQuantity = stockQuantity
        em.persist(book)
        return book
    }

    private fun createMember(): Member {
        val member: Member = Member()
        member.name = "회원1"
        member.address = Address("서울", "강가, ", "123-123")
        em.persist(member)
        return member
    }
}
