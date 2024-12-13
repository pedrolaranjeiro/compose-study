package com.ecommerce.demo.domain

import com.ecommerce.demo.data.models.CartItem
import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow

class SaveProductToCartUseCase(
    private val cartRepository: CartRepository
) {
    fun call(product: Product): Flow<List<CartItem>> {
        return cartRepository.saveProductToCart(product)
    }
}
