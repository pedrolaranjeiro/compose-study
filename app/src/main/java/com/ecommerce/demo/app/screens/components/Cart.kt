package com.ecommerce.demo.app.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ecommerce.demo.app.CartRoute
import com.ecommerce.demo.app.viewmodels.CartViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun Cart(navController: NavHostController) {
    val viewModel = koinViewModel<CartViewModel>()
    val cartList = viewModel.cartItems.collectAsState().value
    var cartSize = 0
    cartList.map {
        cartSize += it.quantity
    }

    LaunchedEffect(Unit) {
        viewModel.getCart()
    }

    val cartValue = if (cartSize == 0) " " else cartSize.toString()
    Row(modifier = Modifier
        .clickable { navController.navigate(CartRoute) }

    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart, contentDescription = "Show Cart",
        )
        Text(
            text = cartValue,
            color = Color.Red,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}
