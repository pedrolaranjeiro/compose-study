package com.ecommerce.demo.app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecommerce.demo.domain.GetProductInfoUseCase
import com.ecommerce.demo.domain.SaveProductToCartUseCase
import com.ecommerce.demo.domain.SearchProductUseCase
import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.data.models.ProductList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ProductViewModel(
    private val searchProductUseCase: SearchProductUseCase,
    private val getProductInfoUseCase: GetProductInfoUseCase,
) : ViewModel() {

    // Shared flow because it will be consumed only once
    private val _isLoading = MutableSharedFlow<Boolean>()
    val isLoading = _isLoading.asSharedFlow()
    private val _errorMessage = MutableStateFlow<String>("")
    val errorMessage = _errorMessage.asSharedFlow()

    // State flow, because it will consumed multiple times.
    private val _productList = MutableStateFlow<ProductList?>(null)
    val products = _productList.asStateFlow()
    private val _productDetails = MutableStateFlow<Product?>(null)
    val productDetails = _productDetails.asStateFlow()

    private var query = ""

    fun fetchProducts(query: String, page: Int = 1) {
        this.query = query
        viewModelScope.launch {
            setupRequest()
            searchProductUseCase.call(query, page).catch {
                _errorMessage.emit("There was an error looking for $query. Please try again later")
                _isLoading.emit(false)
            }.collect {
                _productList.emit(it)
                _isLoading.emit(false)
            }
        }
    }

    fun getProductDetails(asin: String) {
        viewModelScope.launch {
            setupRequest()
            getProductInfoUseCase.call(asin).catch {
                _errorMessage.emit("There was an error fetching the product $asin. Please try again later.")
            }.collect {
                _productDetails.emit(it)
                _isLoading.emit(false)
            }
        }
    }

    private suspend fun setupRequest() {
        _isLoading.emit(true)
        _errorMessage.emit("")
    }

    fun loadPreviousPage() {
        _productList.value?.let {
            val previous = it.page -1
            fetchProducts(query, previous)
        }

    }

    fun loadNextPage() {
        _productList.value?.let {
            val next = it.page +1
            fetchProducts(query, next)
        }
    }
}
