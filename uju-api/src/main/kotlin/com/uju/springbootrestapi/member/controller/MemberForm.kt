package com.uju.springbootrestapi.member.controller

import javax.validation.constraints.NotBlank

class MemberForm {
    @field:NotBlank(message = "이름을 필수입니다.")
    var name: String = ""

    var city: String = ""

    var street: String = ""

    var zipcode: String = ""
}
