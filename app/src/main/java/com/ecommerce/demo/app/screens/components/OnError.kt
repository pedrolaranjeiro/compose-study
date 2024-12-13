package com.ecommerce.demo.app.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ecommerce.demo.app.viewmodels.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnErrorMessage() {
    val viewModel = koinViewModel<ProductViewModel>()
    val errorMessage = viewModel.errorMessage.collectAsState(initial = "")
    if (errorMessage.value.isNotEmpty()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(32.dp)) {
            Text(
                text = errorMessage.value,
                textAlign = TextAlign.Center,
            )
        }
    }
}
