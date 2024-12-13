package com.ecommerce.demo.app.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ecommerce.demo.app.viewmodels.CartViewModel
import com.ecommerce.demo.data.models.CartItem
import org.koin.androidx.compose.koinViewModel

@Composable
fun CartScreen() {

    val viewModel = koinViewModel<CartViewModel>()
    val cartItems = viewModel.cartItems.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.getCart()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Shopping Cart",
            fontSize = 24.sp,
            modifier = Modifier.padding(
                bottom = 32.dp
            )
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(cartItems) { item ->
                CartItemRow(cartItem = item)
            }
        }

        Button(
            onClick = {
                viewModel.clearCart()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Clear Cart")
        }
    }
}

@Composable
fun CartItemRow(cartItem: CartItem) {
    Column {
        Text(text = cartItem.name)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.End,
        ) {
            Text(text = cartItem.quantity.toString(), fontSize = 18.sp, color = Color.Gray)
            Text(text = "x", modifier = Modifier.padding(horizontal = 8.dp))
            Text(text = "${cartItem.price}/item", fontSize = 24.sp, color = Color.Black)
        }
    }
}
