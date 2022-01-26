package com.uju.springbootrestapi.member.controller

import com.uju.springbootrestapi.adderss.Address
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.member.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class MemberThymController(
    private val memberService: MemberService
) {
    @GetMapping("/members/new")
    fun createForm(model: Model): String {
        model.addAttribute("memberForm", MemberForm())
        return "members/createMemberForm"
    }

    @PostMapping("/members/new")
    fun create(@Valid form: MemberForm, result: BindingResult): String {

        if (result.hasErrors()) {
            return "members/createMemberForm"
        }

        val address: Address = Address(form.city, form.street, form.zipcode)
        println("++++=======++++++")
        println(form.name)
        println(form.zipcode)
        val member: Member = Member()
        member.name = form.name
        member.address = address
        println(address)
        memberService.join(member)
        return "redirect:/"
    }

    @GetMapping("/members")
    fun list(model: Model): String {
        val members: List<Member> = memberService.findMembers()
        model.addAttribute("members", members)
        return "members/memberList"
    }
}