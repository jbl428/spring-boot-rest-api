package com.uju.springbootrestapi.order

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class OrderRepository(
    private val em: EntityManager
) {

    fun save(order: Order) {
        em.persist(order)
    }

    fun findOne(id: Long): Order {
        return em.find(Order::class.java, id)
    }

    fun findAll(orderSearch: OrderSearch): List<Order> {
        return em.createQuery(
            "select o from Order o join o.member m " +
                "where o.status = :status " +
                "and m.name like :name",
            Order::class.java
        )
            .setParameter("status", orderSearch.orderStatus)
            .setMaxResults(1000)
            .resultList
    }

    fun findAllWithMemberDelivery(): List<Order> {
        return em.createQuery(
            "select distinct o from Order o " +
                "join fetch o.member m " +
                "join fetch o.delivery d " +
                "join fetch o.orderItems oi " +
                "join fetch oi.item i",
            Order::class.java
        )
            .resultList
    }

    fun findOrderDtos(): List<OrderSimpleQueryDto> {
        return em.createQuery(
            "select new com.uju.springbootrestapi.order.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                "from Order o " +
                "join o.member m " +
                "join o.delivery d",
            OrderSimpleQueryDto::class.java
        ).resultList
    }
}
