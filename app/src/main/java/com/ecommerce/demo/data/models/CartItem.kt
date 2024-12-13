package com.ecommerce.demo.data.models

data class CartItem(
    val id: String,
    val name: String,
    val price: String,
    val quantity: Int = 1
)
