package com.polarbookshop.catalog_service.web

import com.polarbookshop.catalog_service.config.PolarProperties
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(
    private val properties: PolarProperties
) {

    @GetMapping("/")
    fun getGreetings(): String {
        return properties.greeting
    }
}