package com.ecommerce.demo.data.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

data class Product(
    @SerializedName("asin") val asin: String,
    @SerializedName("product_title") val title: String,
    @SerializedName("product_price") val price: String,
    @SerializedName("product_photo") val photoUrl: String,

    // Extras (Not always present)
    @SerializedName("product_availability") val availability: String?,
    @SerializedName("about_product") val about: List<String>?,
    @SerializedName("product_description") val description: String?,
    @SerializedName("product_photos") val photosUrl: List<String>?,
)
