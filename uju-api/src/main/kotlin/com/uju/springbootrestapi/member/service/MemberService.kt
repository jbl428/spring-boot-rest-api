package com.uju.springbootrestapi.member.service

import com.uju.springbootrestapi.member.Member
import com.uju.springbootrestapi.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

@Service
@Transactional(readOnly = true)
class MemberService(
    private val memberRepository: MemberRepository // 자바로 컴파일 하면 기본이 final
) {

    /*
     회원가입
     */
    @Transactional
    fun join(member: Member): Long {
        validateDuplicatedMember(member)
        memberRepository.save(member)

        return member.id
    }

    fun validateDuplicatedMember(member: Member) {
        val findMembers: List<Member> = memberRepository.findByName(member.name)
        if (!findMembers.isEmpty()) {
            throw IllegalStateException("이미 존재하는 회원입니다.")
        }
    }

    // 회원 전체 조회
    fun findMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun findOne(memberId: Long): Member {
        return memberRepository.findOne(memberId)
    }

    @Transactional
    fun update(id: Long, name: String): Unit {
        val member: Member = memberRepository.findOne(id)
        member.name = name
    }
}