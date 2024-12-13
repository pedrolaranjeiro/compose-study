package com.ecommerce.demo.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ecommerce.demo.app.screens.components.AddToCart
import com.ecommerce.demo.app.screens.components.Cart
import com.ecommerce.demo.app.screens.components.OnErrorMessage
import com.ecommerce.demo.app.screens.components.ProductImage
import com.ecommerce.demo.app.viewmodels.ProductViewModel
import com.ecommerce.demo.data.models.Product
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductDetailsScreen(navController: NavHostController, asin: String) {
    val productViewModel = koinViewModel<ProductViewModel>()
    val isLoading = productViewModel.isLoading.collectAsState(initial = false).value
    val product = productViewModel.productDetails.collectAsState().value
    val errorMessage = productViewModel.errorMessage.collectAsState(initial = "")

    LaunchedEffect(Unit) {
        productViewModel.getProductDetails(asin)
    }

    if (errorMessage.value.isNotEmpty()) {
        OnErrorMessage()
    } else if (isLoading) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(32.dp)) {
            LinearProgressIndicator()
        }

    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.padding(32.dp)) {
                Cart(navController)
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    ProductDetails(product)
                }
                item {
                    AddToCart(
                        product, modifier = Modifier
                            .fillMaxWidth()
                            .padding(48.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProductDetails(product: Product?) {
    ProductImage(
        imageUrl = product?.photoUrl.orEmpty(),
        description = "Big Image",
        modifier = Modifier
            .padding(bottom = 32.dp)
            .height(400.dp)
    )
    ImagesRow(product?.photosUrl)
    Text(text = "${product?.title}", fontSize = 24.sp, modifier = Modifier.fillMaxWidth())
    Text(text = "Price: ${product?.price}", modifier = Modifier.fillMaxWidth())
    Text(
        text = "Availability: ${product?.availability}",
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    )
    Text(text = product?.description.orEmpty())
}

@Composable
private fun ImagesRow(photosUrl: List<String>?) {
    val productViewModel = koinViewModel<ProductViewModel>()
    val product = productViewModel.productDetails.collectAsState().value

    photosUrl?.let {
        LazyRow(modifier = Modifier.padding(top = 32.dp, bottom = 32.dp)) {
            items(it) { item ->
                ProductImage(
                    imageUrl = item,
                    description = "Small Image", modifier =
                    Modifier
                        .height(100.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}



