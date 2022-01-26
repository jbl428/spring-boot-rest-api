package com.uju.springbootrestapi.item

import org.springframework.stereotype.Repository
import javax.persistence.EntityManager

@Repository
class ItemRepository(
    private val em: EntityManager
) {
    fun save(item: Item): Unit {
        if (item.id == null) {
            em.persist(item)
        } else {
            em.merge(item)
        }
    }

    fun findOne(id: Long): Item  {
        return em.find(Item::class.java, id)
    }

    fun findAll(): List<Item> {
        return em.createQuery("select i from Item i", Item::class.java).resultList
    }
}