package com.uju.springbootrestapi.order.controller

import com.uju.springbootrestapi.item.Item
import com.uju.springbootrestapi.item.ItemService
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.member.service.MemberService
import com.uju.springbootrestapi.order.Order
import com.uju.springbootrestapi.order.OrderSearch
import com.uju.springbootrestapi.order.service.OrderService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.ui.Model as Model1

@Controller
class OrderThymController(
    private val orderService: OrderService,
    private val memberService: MemberService,
    private val itemService: ItemService
) {
    @GetMapping("/order")
    fun creatForm(model: Model1): String {
        val members: List<Member> = memberService.findMembers()
        val items: List<Item> = itemService.findItems()

        model.addAttribute("members", members)
        model.addAttribute("items", items)

        return "order/orderForm"
    }

    @PostMapping("/order")
    fun order(
        @RequestParam("memberId") memeberId: Long,
        @RequestParam("itemId") itemId: Long,
        @RequestParam("count") count: Int
    ): String {
        orderService.order(memeberId, itemId, count)
        return "redirect:/orders"
    }

    @GetMapping("/orders")
    fun orderList(@ModelAttribute("orderSearch") orderSearch: OrderSearch, model: Model1): String {
        val orders: List<Order> = orderService.findOrders(orderSearch)
        model.addAttribute("orders", orders)

        return "order/orderList"
    }
}
