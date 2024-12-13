package com.ecommerce.demo.domain

import com.ecommerce.demo.domain.repositories.CartRepository

class ClearCartUseCase(
    private val cartRepository: CartRepository
) {
    fun call() = cartRepository.clear()
}
