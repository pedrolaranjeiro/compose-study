package com.ecommerce.demo.data.local

import com.ecommerce.demo.data.models.CartItem
import com.ecommerce.demo.data.models.Product
import kotlinx.coroutines.flow.flow

class LocalDataSource {

    private val cart = hashMapOf<String, CartItem>()

    fun addToCart(product: Product) =
        flow {
            var cartItem = cart[product.asin]
            if (cartItem == null) {
                cartItem = CartItem(
                    id = product.asin,
                    name = product.title,
                    price = product.price,
                    quantity = 0
                )
                cart[product.asin] = cartItem
            }
            updateQuantity(cartItem.id, cartItem.quantity + 1)
            emit(cart.values.toList())
        }


    private fun updateQuantity(id: String, quantity: Int) {
        val cartItem = cart[id]
        cartItem?.let {
            cart[id] = it.copy(quantity = quantity)
        }
    }

    fun clearCart() = flow {
        cart.clear()
        emit(Unit)
    }

    fun getCart() = flow {
        emit(cart.values.toList())
    }

}
