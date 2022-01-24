package com.uju.springbootrestapi.category

import com.uju.springbootrestapi.item.Item
import javax.persistence.*

@Entity
class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    var id: Long = 0
    
    var name: String = ""

    @ManyToMany
    @JoinTable(name = "category_item",
        joinColumns = [JoinColumn(name = "category_id")],
        inverseJoinColumns = [JoinColumn(name = "item_id")]
    )
    var items: MutableList<Item> = mutableListOf()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    lateinit var parent: Category

    @OneToMany(mappedBy = "parent")
    val child: MutableList<Category> = mutableListOf()

    // 연관관계 메서드
    fun addChildCategory(child: Category): Unit {
        this.child.add(child)
        child.parent = this
    }
}