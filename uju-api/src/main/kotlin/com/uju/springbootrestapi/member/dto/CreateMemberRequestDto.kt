package com.uju.springbootrestapi.member.dto

import javax.validation.constraints.NotBlank

data class CreateMemberRequestDto(
    @field:NotBlank(message = "이름은 필수로 입력해야합니다.")
    val name: String
)
