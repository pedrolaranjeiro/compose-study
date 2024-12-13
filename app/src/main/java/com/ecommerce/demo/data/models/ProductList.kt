package com.ecommerce.demo.data.models

import com.ecommerce.demo.data.models.Product

data class ProductList(
    val page: Int = 1,
    val totalPages: Int = 1,
    val products: List<Product>
) {
    fun isLastPage(): Boolean {
        return page == totalPages
    }

    fun isFirstPage(): Boolean {
        return page == 1
    }
}
