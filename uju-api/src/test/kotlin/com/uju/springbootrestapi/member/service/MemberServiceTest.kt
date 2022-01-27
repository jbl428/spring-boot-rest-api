package com.uju.springbootrestapi.member.service

import com.uju.springbootrestapi.ApiApplication
import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.member.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [ApiApplication::class])
@Transactional
internal class MemberServiceTest @Autowired constructor(
    private val memberService: MemberService,
    private val memberRepository: MemberRepository
) {

    @Test
    fun `회원가입`() {
        // given
        var member: Member = Member()
        member.name = "kim"

        // when
        val savedId: Long = memberService.join(member)

        // then
        assertEquals(member, memberRepository.findOne(savedId))
    }

    @Test()
    fun `중복 회원 예외`() {
        // given
        var member1: Member = Member()
        member1.name = "kim"

        var member2: Member = Member()
        member2.name = "kim"

        // when
        memberService.join(member1)

        // then
        val thrown: IllegalStateException = Assertions.assertThrows(IllegalStateException::class.java) {
            memberService.join(member2)
        }
        assertEquals("이미 존재하는 회원입니다.", thrown.message)
    }
}
