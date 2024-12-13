package com.ecommerce.demo.domain

import com.ecommerce.demo.domain.repositories.CartRepository

class GetCartUseCase (
    private val cartRepository: CartRepository
) {
    fun call() = cartRepository.getCart()

}
