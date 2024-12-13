package com.ecommerce.demo.data.local

import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.data.models.ProductList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockDataSource {

    fun search(query: String, page: Int): Flow<ProductList> = flow {
        val products= listOf(
            mockProduct,
            mockProduct,
            mockProduct,
            mockProduct,
        )
        emit(ProductList(products = products, totalPages = 2, page = page))
    }

    fun getProductInfo(asin: String): Flow<Product?> = flow {
        //throw Exception("test exception")
        emit(mockProduct)
    }

}
