package com.ecommerce.demo.app.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest

@Composable
fun ProductImage(imageUrl: String, description: String = "", modifier: Modifier = Modifier){
    val context = LocalContext.current
    val imageRequest = ImageRequest.Builder(context)
        .data(imageUrl)
        .memoryCacheKey(imageUrl)
        .diskCacheKey(imageUrl)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .build()

    AsyncImage(
        model = imageRequest,
        contentDescription = description,
        modifier = modifier
    )
}

