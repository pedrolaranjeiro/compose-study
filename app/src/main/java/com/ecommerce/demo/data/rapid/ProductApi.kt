package com.ecommerce.demo.data.rapid

import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi {

    @GET("search")
    suspend fun getProducts(@Query("query") query: String, @Query("page") page: Int=1): ProductListResponse

    @GET("product-details")
    suspend fun getProductDetails(@Query("asin") asin: String): ProductDetailsResponse

}
