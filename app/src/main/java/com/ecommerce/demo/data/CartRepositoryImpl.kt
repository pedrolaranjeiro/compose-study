package com.ecommerce.demo.data

import com.ecommerce.demo.data.local.LocalDataSource
import com.ecommerce.demo.data.models.CartItem
import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.data.models.ProductList
import com.ecommerce.demo.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow

class CartRepositoryImpl(
    private val dataSource: LocalDataSource
) : CartRepository {

    override fun saveProductToCart(product: Product) =
        dataSource.addToCart(product)


    override fun getCart() = dataSource.getCart()
    override fun clear() = dataSource.clearCart()

}
