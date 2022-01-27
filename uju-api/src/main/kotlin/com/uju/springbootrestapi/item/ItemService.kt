package com.uju.springbootrestapi.item

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ItemService(
    private val itemRepository: ItemRepository
) {

    @Transactional
    fun saveItem(item: Item) {
        itemRepository.save(item)
    }

    fun findItems(): List<Item> {
        return itemRepository.findAll()
    }

    fun findOne(itemId: Long): Item {
        return itemRepository.findOne(itemId)
    }
}
