package com.uju.springbootrestapi.test.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HelloController {

    @RequestMapping("/")
    fun hello(model: Model): String {

        return "home"
    }
}
