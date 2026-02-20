package com.polarbookshop.catalog_service.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {

    @GetMapping("/")
    fun getGreetings(): String {
        return "도서 카달로그에 오신 것을 환영합니다!"
    }
}