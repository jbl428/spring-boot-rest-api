package com.uju.springbootrestapi.item

import com.uju.springbootrestapi.category.Category
import com.uju.springbootrestapi.delivery.Delivery
import com.uju.springbootrestapi.exception.NotEnoughStockException
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.orderItem.OrderItem
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    var id: Long = 0

    var name: String = ""

    var price: Int = 0

    var stockQuantity: Int = 0

    @ManyToMany(mappedBy = "items")
    var categories: MutableList<Category> = mutableListOf()

    // 비즈니스 로직
    fun addStock(quantity: Int): Unit {
        this.stockQuantity += quantity
    }

    fun removeStock(quantity: Int): Unit {
        val restStock = this.stockQuantity - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity = restStock
    }
}
