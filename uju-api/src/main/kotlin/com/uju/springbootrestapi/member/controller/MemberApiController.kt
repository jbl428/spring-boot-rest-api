package com.uju.springbootrestapi.member.controller

import com.uju.springbootrestapi.common.ResultResponse
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.member.dto.CreateMemberRequestDto
import com.uju.springbootrestapi.member.dto.CreateMemberResponseDto
import com.uju.springbootrestapi.member.dto.MemberDto
import com.uju.springbootrestapi.member.dto.UpdateMemberRequestDto
import com.uju.springbootrestapi.member.dto.UpdateMemberResponseDto
import com.uju.springbootrestapi.member.service.MemberService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class MemberApiController(
    private val memberService: MemberService
) {
    @PostMapping("/api/v1/members")
    fun saveMember(@RequestBody @Valid createMemberRequestDto: CreateMemberRequestDto): CreateMemberResponseDto {
        val member: Member = Member()
        member.name = createMemberRequestDto.name

        val id: Long = memberService.join(member)
        return CreateMemberResponseDto(id)
    }

    @PutMapping("/api/v1/members/{id}")
    fun updateMember(
        @PathVariable("id") id: Long,
        @RequestBody @Valid updateMemberRequestDto: UpdateMemberRequestDto
    ): UpdateMemberResponseDto {
        memberService.update(id, updateMemberRequestDto.name)
        val findMember: Member = memberService.findOne(id)
        return UpdateMemberResponseDto(findMember.id, findMember.name)
    }

    @GetMapping("/api/v1/members")
    fun member(): ResultResponse<List<MemberDto>> {
        val findMembers: List<MemberDto> = memberService.findMembers()
            .map {
                MemberDto(it.name)
            }

        return ResultResponse(findMembers, findMembers.size)
    }
}
