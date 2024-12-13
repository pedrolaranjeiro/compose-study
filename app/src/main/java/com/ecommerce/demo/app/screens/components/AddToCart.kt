package com.ecommerce.demo.app.screens.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ecommerce.demo.app.viewmodels.CartViewModel
import com.ecommerce.demo.data.models.Product
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddToCart(product: Product?, modifier: Modifier = Modifier) {
    val cartViewModel = koinViewModel<CartViewModel>()
    product?.let {
        Button(onClick = {
            cartViewModel.addToCart(product)
        }, modifier = modifier) {
            Text(text = "Add to cart")
        }
    }
}
