package com.ecommerce.demo.data.rapid

import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.data.models.ProductList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RapidDataSource(
    private val api: ProductApi
) {

    fun search(query: String, page: Int): Flow<ProductList> = flow {
        val response = api.getProducts(query, page)
        emit(
            ProductList(
                products = response.data.products.orEmpty(),
                page = response.parameters.page ?: 1,
                totalPages = getTotalPages(response)
            )
        )
    }

    fun getProductInfo(asin: String): Flow<Product?> = flow {
        var product: Product? = null
        val response = api.getProductDetails(asin)
        product = response.data
        emit(product)
    }

    private fun getTotalPages(response: ProductListResponse): Int {
        if (response.data.totalProducts == null) return 1
        if (response.data.products == null) return 1
        return response.data.totalProducts / response.data.products.size

    }

}
