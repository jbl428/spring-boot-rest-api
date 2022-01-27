package com.uju.springbootrestapi.item

import com.uju.springbootrestapi.category.Category
import com.uju.springbootrestapi.exception.NotEnoughStockException
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.ManyToMany

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
    fun addStock(quantity: Int) {
        this.stockQuantity += quantity
    }

    fun removeStock(quantity: Int) {
        val restStock = this.stockQuantity - quantity
        if (restStock < 0) {
            throw NotEnoughStockException("need more stock")
        }
        this.stockQuantity = restStock
    }
}
