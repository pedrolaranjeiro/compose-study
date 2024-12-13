package com.ecommerce.demo.data

import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.data.models.ProductList
import com.ecommerce.demo.data.rapid.RapidDataSource
import com.ecommerce.demo.domain.repositories.ProductRepository
import kotlinx.coroutines.flow.Flow

class ProductRepositoryImpl (
    private val dataSource: RapidDataSource,
): ProductRepository {

    override fun search(query: String, page: Int): Flow<ProductList> {
        return dataSource.search(query, page)
    }

    override fun getProductInfo(productId: String): Flow<Product?> {
        return dataSource.getProductInfo(productId)
    }
}
