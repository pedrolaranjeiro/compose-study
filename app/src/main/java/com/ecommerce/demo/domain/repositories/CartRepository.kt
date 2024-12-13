package com.ecommerce.demo.domain.repositories

import com.ecommerce.demo.data.models.CartItem
import com.ecommerce.demo.data.models.Product
import kotlinx.coroutines.flow.Flow

interface CartRepository {

    fun saveProductToCart(product: Product): Flow<List<CartItem>>

    fun getCart(): Flow<List<CartItem>>

    fun clear(): Flow<Unit>

}
