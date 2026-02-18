package com.polarbookshop.catalog_service

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun getGreetings(): String {
        return "도서 카달로그에 오신 것을 환영합니다!"
    }
}