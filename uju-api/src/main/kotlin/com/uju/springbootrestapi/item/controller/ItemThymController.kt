package com.uju.springbootrestapi.item.controller

import com.uju.springbootrestapi.item.Book
import com.uju.springbootrestapi.item.BookForm
import com.uju.springbootrestapi.item.Item
import com.uju.springbootrestapi.item.ItemService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class ItemThymController(
    private val itemService: ItemService
) {
    @GetMapping("/items/new")
    fun createForm(model: Model): String {
        model.addAttribute("form", BookForm())
        return "items/createItemForm"
    }

    @PostMapping("/items/new")
    fun create(form: BookForm): String {
        val book: Book = Book()
        book.name = form.name
        book.price = form.price
        book.stockQuantity = form.stockQuantity
        book.author = form.author
        book.isbn = form.isbn
        println(form.name)
        println(form.isbn)
        itemService.saveItem(book)
        return "redirect:/"
    }

    @GetMapping("/items")
    fun list(model: Model): String {
        val items: List<Item> = itemService.findItems()
        model.addAttribute("items", items)
        return "items/itemList"
    }

    @GetMapping("items/{itemId}/edit")
    fun updateItemForm(@PathVariable("itemId") itemId: Long, model: Model): String {
        val item: Book = itemService.findOne(itemId) as Book

        val form: BookForm = BookForm()
        form.id = item.id
        form.name = item.name
        form.price = item.price
        form.stockQuantity = item.stockQuantity
        form.author = item.author
        form.isbn = item.isbn

        model.addAttribute("form", form)
        return "items/updateItemForm"
    }

    @PostMapping("items/{itemId}/edit")
    fun updateItem(@PathVariable("itemId") itemId: Long, @ModelAttribute("form") form: BookForm): String {
        val book: Book = Book()
        book.name = form.name
        book.price = form.price
        book.stockQuantity = form.stockQuantity
        book.author = form.author
        book.isbn = form.isbn
        book.id = form.id

        itemService.saveItem(book)
        return "redirect:/items"
    }
}
