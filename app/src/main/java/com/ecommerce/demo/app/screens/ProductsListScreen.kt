package com.ecommerce.demo.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.ecommerce.demo.app.screens.components.AddToCart
import com.ecommerce.demo.app.screens.components.OnErrorMessage
import com.ecommerce.demo.app.screens.components.ProductImage
import com.ecommerce.demo.app.viewmodels.ProductViewModel
import com.ecommerce.demo.data.models.Product
import com.ecommerce.demo.app.ProductDetailsRoute
import com.ecommerce.demo.app.screens.components.Cart
import com.ecommerce.demo.data.models.ProductList
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsListScreen(navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopRow(navController)
        Loading()
        ProductList(navController)
        OnErrorMessage()
    }
}

@Composable
fun TopRow(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Searchbar(
            modifier = Modifier
                .weight(1f)
                .background(Color.White)
                .padding(end = 8.dp)
        )
        Cart(navController)
    }
}

@Composable
private fun Searchbar(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<ProductViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current

    val query = remember {
        mutableStateOf("")
    }
    TextField(
        value = query.value,
        onValueChange = { query.value = it },
        label = { Text(text = "Phones, tablets, dishwashers,...") },
        keyboardActions = KeyboardActions(onSearch = {
            viewModel.fetchProducts(query.value)
            keyboardController?.hide()
        }),

        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun Loading() {
    val productViewModel = koinViewModel<ProductViewModel>()
    if (productViewModel.isLoading.collectAsState(initial = false).value) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun ProductList(navController: NavHostController) {
    val productViewModel = koinViewModel<ProductViewModel>()
    val productList: ProductList? = productViewModel.products.collectAsState().value

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {//
        items(items = productList?.products.orEmpty(), itemContent = { product ->
            ProductDetails(navController, product = product)
        })
        item {
            PageSwitcher()
        }
    }
}

@Composable
private fun ProductDetails(navController: NavController, product: Product) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colorScheme.background)
        .clickable {
            navController.navigate(
                ProductDetailsRoute(
                    product.asin
                )
            )
        }) {
        Column {
            ProductImage(
                imageUrl = product.photoUrl,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Text(text = product.title, fontSize = 18.sp)
            Text(text = "Price: ${product.price}")
            AddToCart(
                product = product,
                modifier = Modifier
                    .align(alignment = Alignment.End)
                    .padding(16.dp)
            )
            Divider()
        }
    }
}

@Composable
fun PageSwitcher() {
    val productViewModel = koinViewModel<ProductViewModel>()
    val productList: ProductList? = productViewModel.products.collectAsState().value
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        productList?.let {
            // if is first page, hide back button
            Button(
                onClick = { productViewModel.loadPreviousPage() },
                enabled = !productList.isFirstPage()
            ) {
                Text(text = "Previous")
            }

            // if is last page, hide next button
            Button(
                onClick = { productViewModel.loadNextPage() },
                enabled = !productList.isLastPage()
            ) {
                Text(text = "Next")
            }
        }
    }
}


