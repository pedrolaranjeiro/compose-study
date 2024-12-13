package com.ecommerce.demo.domain

import com.ecommerce.demo.data.models.ProductList
import com.ecommerce.demo.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow

class SearchProductUseCase (
    private val productRepository: ProductRepository
) {
    fun call(query: String, page: Int): Flow<ProductList> {
        return productRepository.search(query, page)
    }
}

