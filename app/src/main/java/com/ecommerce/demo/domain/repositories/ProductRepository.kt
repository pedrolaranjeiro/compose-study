package com.ecommerce.demo.domain.repositories

import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.data.models.ProductList
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun search(query: String, page:Int = 1): Flow<ProductList>

     fun getProductInfo(productId: String): Flow<Product?>

}
