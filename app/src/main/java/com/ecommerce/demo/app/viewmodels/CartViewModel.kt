package com.ecommerce.demo.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.demo.data.models.CartItem
import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.domain.ClearCartUseCase
import com.ecommerce.demo.domain.GetCartUseCase
import com.ecommerce.demo.domain.SaveProductToCartUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val saveProductToCart: SaveProductToCartUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val clearCartUseCase: ClearCartUseCase,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems

    fun getCart() {
        viewModelScope.launch(coroutineDispatcher) {
            getCartUseCase.call().collect {
                _cartItems.value = it
            }
        }
    }

    fun addToCart(product: Product) {
        viewModelScope.launch(coroutineDispatcher) {
            saveProductToCart.call(product).collect {
                _cartItems.value = it
            }
        }
    }

    fun clearCart() {
        viewModelScope.launch(coroutineDispatcher) {
            clearCartUseCase.call().collect {
                _cartItems.value = emptyList()
            }
        }
    }
}
