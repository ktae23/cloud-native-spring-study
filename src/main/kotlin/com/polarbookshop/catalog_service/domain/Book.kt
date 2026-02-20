package com.polarbookshop.catalog_service.domain

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val price: Double,
    )
