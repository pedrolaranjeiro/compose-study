package com.ecommerce.demo.data.rapid

import com.ecommerce.demo.data.models.Product
import com.google.gson.annotations.SerializedName

data class ProductDetailsResponse(
    @SerializedName("status") val status: String,
    @SerializedName("parameters") val parameters: ProductParameters,
    @SerializedName("data") val data: Product
)

data class ProductListResponse(
    @SerializedName("status") val status: String,
    @SerializedName("parameters") val parameters: ProductParameters,
    @SerializedName("data") val data: ProductData
)

data class ProductParameters(
    @SerializedName("country") val country: String,
    @SerializedName("page") val page: Int?,
    @SerializedName("asin") val asin: String?
)

data class ProductData(
    @SerializedName("total_products") val totalProducts: Int?,
    @SerializedName("products") val products: List<Product>?,
    @SerializedName("product_description") val productDescription: String?,
    @SerializedName("product_price") val productPrice: String?,
    @SerializedName("product_title") val productTitle: String?,
    @SerializedName("product_photos") val productPhotos: List<String>?
)
