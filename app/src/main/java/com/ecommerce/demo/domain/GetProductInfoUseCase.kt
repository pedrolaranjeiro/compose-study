package com.ecommerce.demo.domain

import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductInfoUseCase(
    private val productRepository: ProductRepository
) {
    fun call(asin: String): Flow<Product?> {
        return productRepository.getProductInfo(asin)
    }
}
